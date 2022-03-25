package com.example.workoutscardio.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class WorkoutModel() : Parcelable {

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("duration")
    @Expose
    private var duration: String? = null

    @SerializedName("views")
    @Expose
    private var views: String? = null

    @SerializedName("tags")
    @Expose
    private var tags: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("trainer")
    @Expose
    private var trainer: String? = null

    @SerializedName("difficulty")
    @Expose
    private var difficulty: String? = null

    @SerializedName("video_url")
    @Expose
    private var videoUrl: String? = null

    @SerializedName("category")
    @Expose
    private var category: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        duration = parcel.readString()
        views = parcel.readString()
        tags = parcel.readString()
        description = parcel.readString()
        type = parcel.readString()
        trainer = parcel.readString()
        difficulty = parcel.readString()
        videoUrl = parcel.readString()
        category = parcel.readString()
    }


    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDuration(): String? {
        return duration
    }

    fun setDuration(duration: String?) {
        this.duration = duration
    }

    fun getViews(): String? {
        return views
    }

    fun setViews(views: String?) {
        this.views = views
    }

    fun getTags(): String? {
        return tags
    }

    fun setTags(tags: String?) {
        this.tags = tags
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun getTrainer(): String? {
        return trainer
    }

    fun setTrainer(trainer: String?) {
        this.trainer = trainer
    }

    fun getDifficulty(): String? {
        return difficulty
    }

    fun setDifficulty(difficulty: String?) {
        this.difficulty = difficulty
    }

    fun getVideoUrl(): String? {
        return videoUrl
    }

    fun setVideoUrl(videoUrl: String?) {
        this.videoUrl = videoUrl
    }

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(duration)
        parcel.writeString(views)
        parcel.writeString(tags)
        parcel.writeString(description)
        parcel.writeString(type)
        parcel.writeString(trainer)
        parcel.writeString(difficulty)
        parcel.writeString(videoUrl)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WorkoutModel> {
        override fun createFromParcel(parcel: Parcel): WorkoutModel {
            return WorkoutModel(parcel)
        }

        override fun newArray(size: Int): Array<WorkoutModel?> {
            return arrayOfNulls(size)
        }
    }

}