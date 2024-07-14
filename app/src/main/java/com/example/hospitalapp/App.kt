package com.example.hospitalapp

import android.app.Application
import com.example.hospitalapp.framework.database.MySharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(this)
    }
}