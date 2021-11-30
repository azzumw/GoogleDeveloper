package com.example.diceroller

import android.util.Log

object Dice{

    internal const val sides:Int = 6

    fun roll() : Int {


        Log.e(this.javaClass.canonicalName, getRandom().toString())

        return when(getRandom()){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

    }

    fun getRandom() = (1..sides).random()

}