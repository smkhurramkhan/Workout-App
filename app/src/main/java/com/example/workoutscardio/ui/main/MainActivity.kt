package com.example.workoutscardio.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutscardio.R
import com.example.workoutscardio.data.model.WorkoutModel
import com.example.workoutscardio.data.viewmodel.WorkoutViewModel
import com.example.workoutscardio.databinding.ActivityMainBinding
import com.example.workoutscardio.room.entity.FavouriteEntity
import com.example.workoutscardio.ui.main.adapters.MainAdapter
import com.example.workoutscardio.utils.Status
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var favList: MutableList<FavouriteEntity> = mutableListOf()
    private var workOutList: MutableList<WorkoutModel> = mutableListOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    val viewModel: WorkoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()


        getData()


        viewModel.workoutRepository.getFavourite().observe(this, {
            Timber.d("favlist size is ${favList.size}")
            favList.addAll(it)
        })

        viewModel.setRemoveAddCallback {
            if (it) {
                Toast.makeText(
                    this,
                    "Added in Favourite record",
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                Toast.makeText(
                    this,
                    "removed from Favourite record",
                    Toast.LENGTH_LONG
                )
                    .show()

            }
        }
    }

    private fun initToolbar() {
        binding.toolbar.title = getString(R.string.app_name)

    }

    private fun getData() {
        viewModel.fetchWorkouts().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressbar.hide()
                    Timber.d("data coming from api is ${Gson().toJson(it)}")
                    workOutList.addAll(it.data!!)

                    mainAdapter = MainAdapter(workOutList,
                        this,
                        favList,
                        callBack = { position ->
                            toggleSelection(position)
                            viewModel.addRemoveFavourite(workOutList[position])
                        }
                    )
                    binding.rvWorkouts.layoutManager = LinearLayoutManager(this)
                    binding.rvWorkouts.adapter = mainAdapter


                }
                Status.ERROR -> {
                    Timber.d("data coming from api is ${it.message}")
                    binding.progressbar.hide()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.progressbar.show()
                    Timber.d("Loading Status")

                }
            }
        })
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }


    private fun toggleSelection(position: Int) {
        try {
            mainAdapter.toggleSelection(position)
            val count: Int = mainAdapter.getSelectedItemCount()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}