package com.example.diceroller

import android.util.Log
import androidx.lifecycle.ViewModel

object Dice : ViewModel(){

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