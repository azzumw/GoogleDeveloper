package com.example.diceroller

import android.util.Log

class Dice {
    private val _sides : Int
    get() = 6
    val sides = _sides

    fun roll() : Int {
        val rand = (1..6).random()
        Log.e(this.javaClass.canonicalName,rand.toString())
        return when(rand){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

    }
}