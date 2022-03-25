package com.example.workoutscardio.data.api

import com.example.workoutscardio.data.model.WorkoutModel
import retrofit2.http.GET

interface ApiService {

    @GET("GetWorkouts.php")
    suspend fun getUsers(): List<WorkoutModel>
}