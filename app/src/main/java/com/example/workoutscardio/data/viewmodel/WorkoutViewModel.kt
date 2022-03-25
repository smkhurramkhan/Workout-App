package com.example.workoutscardio.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.workoutscardio.data.model.WorkoutModel
import com.example.workoutscardio.data.repository.WorkoutRepository
import com.example.workoutscardio.room.entity.FavouriteEntity
import com.example.workoutscardio.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    val workoutRepository: WorkoutRepository,

    ) : ViewModel() {

    lateinit var callback: (flag: Boolean) -> Unit

    fun setRemoveAddCallback(callback: (flag: Boolean) -> Unit) {
        this.callback = callback
    }


    fun fetchWorkouts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = workoutRepository.getWorkouts()))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }


    fun addFavourite(workoutModel: WorkoutModel?) {
        val favouriteEntity = FavouriteEntity()
        workoutModel?.let {
            favouriteEntity.id = it.getId()?.toInt()!!
            favouriteEntity.name = it.getName()
            favouriteEntity.category = it.getCategory()
            favouriteEntity.description = it.getDescription()
            favouriteEntity.difficulty = it.getDifficulty()
            favouriteEntity.duration = it.getDuration()
            favouriteEntity.tags = it.getTags()
            favouriteEntity.trainer = it.getTrainer()
            favouriteEntity.type = it.getType()
            favouriteEntity.videoUrl = it.getVideoUrl()
            favouriteEntity.views = it.getViews()
            workoutRepository.addFavourite(favouriteEntity)
        }
    }


    private fun removeFavourite(drinkid: String) {
        val id = drinkid.toInt()
        workoutRepository.removeFavourite(id)

    }

    fun addRemoveFavourite(workoutModel: WorkoutModel?) {
        viewModelScope.launch {

            if (workoutRepository.farvouriteDao.getSpecificFavourite(
                    workoutModel?.getId()!!.toInt()
                ) == 0
            ) {
                addFavourite(workoutModel)
                callback(true)
            } else {
                removeFavourite(workoutModel.getId()!!)
                callback(false)


            }
        }

    }

}