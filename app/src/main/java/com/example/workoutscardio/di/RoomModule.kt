package com.example.workoutscardio.di

import android.content.Context
import androidx.room.Room
import com.example.workoutscardio.room.AppDatabase
import com.example.workoutscardio.room.dao.FarvouriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "workoutsDb"
        )
            .build()
    }

    @Provides
    fun provideClientDao(appDatabase: AppDatabase): FarvouriteDao {
        return appDatabase.farvouriteDao()
    }


}