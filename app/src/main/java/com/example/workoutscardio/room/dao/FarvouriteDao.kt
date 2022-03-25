package com.example.workoutscardio.room.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.workoutscardio.room.entity.FavouriteEntity

@Dao
interface FarvouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favouriteEntity: FavouriteEntity)

    @Query("delete from FavouriteEntity where id=:workoutID")
    suspend fun removeFavourite(workoutID : Int)

    @Query("select  count(*) from FavouriteEntity where id=:workoutID")
    suspend fun getSpecificFavourite(workoutID : Int) : Int

    @Query("select  * from FavouriteEntity")
    suspend fun getSpecificFavourite() : List<FavouriteEntity>


}