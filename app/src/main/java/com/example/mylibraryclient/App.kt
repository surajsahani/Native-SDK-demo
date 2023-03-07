package com.example.mylibraryclient

import android.app.Application
import com.lib.loopsdk.auth.LoopInstance

class App: Application() {

    override fun onCreate() {
        LoopInstance.initialize(this)
        super.onCreate()
    }
}