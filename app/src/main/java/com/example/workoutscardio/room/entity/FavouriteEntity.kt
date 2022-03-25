package com.example.workoutscardio.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavouriteEntity {

    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var duration: String? = null
    var views: String? = null
    var tags: String? = null
    var description: String? = null
    var type: String? = null
    var trainer: String? = null
    var difficulty: String? = null
    var videoUrl: String? = null
    var category: String? = null
}
