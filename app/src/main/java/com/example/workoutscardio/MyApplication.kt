package com.example.workoutscardio

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.jetbrains.annotations.NotNull
import timber.log.Timber


@HiltAndroidApp
open class MyApplication : Application() {
    val hTag = "khurramTag %s"

    override fun onCreate() {
        super.onCreate()

       initTimber()

    }


    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(
                    priority: Int,
                    tag: String?,
                    @NotNull message: String,
                    t: Throwable?
                ) {
                    super.log(priority, String.format(hTag, tag), message, t)
                }
            })
        }
    }

}