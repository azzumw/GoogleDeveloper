package com.example.diceroller

import android.util.Log

class MainPresenter() {

    fun roll() : Int {


        Log.e(this.javaClass.canonicalName, Dice.getRandom().toString())

        return when(Dice.getRandom()){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}