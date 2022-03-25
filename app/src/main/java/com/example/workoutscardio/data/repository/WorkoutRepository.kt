package com.example.workoutscardio.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.workoutscardio.data.api.ApiService
import com.example.workoutscardio.data.model.WorkoutModel
import com.example.workoutscardio.room.dao.FarvouriteDao
import com.example.workoutscardio.room.entity.FavouriteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val apiService: ApiService,
    val farvouriteDao: FarvouriteDao,
) {

    suspend fun getWorkouts(): List<WorkoutModel> {
        return apiService.getUsers()
    }


    fun addFavourite(favouriteEntity: FavouriteEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            farvouriteDao.insertFavourite(favouriteEntity)
        }
    }

    fun removeFavourite(drinkid: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            farvouriteDao.removeFavourite(drinkid)
        }
    }

    fun getFavourite(): LiveData<List<FavouriteEntity>> = liveData(Dispatchers.IO)
    {
        emit(farvouriteDao.getSpecificFavourite())
    }

    suspend fun getSpecificFavourite(workoutid: Int): Int {

        return farvouriteDao.getSpecificFavourite(workoutid)
    }

}