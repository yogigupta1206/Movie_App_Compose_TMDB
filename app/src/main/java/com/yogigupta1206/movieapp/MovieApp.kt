package com.yogigupta1206.movieapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : Application() {

    companion object {
        private const val TAG = "MovieApp"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: MovieApp")
    }
}