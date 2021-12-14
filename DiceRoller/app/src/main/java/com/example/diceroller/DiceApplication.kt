package com.example.diceroller

import android.app.Application
import android.util.Log

class DiceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(this.javaClass.canonicalName,"Hello")
    }
}