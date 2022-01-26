package com.example.diceroller

import android.app.Application
import android.util.Log

class DiceApplication : Application() {

    lateinit var dice:Dice

    override fun onCreate() {
        super.onCreate()
        Log.d(this.javaClass.canonicalName,"Hello")

        dice = RealDice()
    }
}