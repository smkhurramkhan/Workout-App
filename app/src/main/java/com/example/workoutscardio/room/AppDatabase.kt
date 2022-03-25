package com.example.workoutscardio.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workoutscardio.room.dao.FarvouriteDao
import com.example.workoutscardio.room.entity.FavouriteEntity


@Database(
    entities = [
        FavouriteEntity::class,

    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun farvouriteDao(): FarvouriteDao

}