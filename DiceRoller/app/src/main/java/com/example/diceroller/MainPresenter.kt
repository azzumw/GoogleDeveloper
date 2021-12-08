package com.example.diceroller

class MainPresenter(private val dice:Dice) {

    fun getDrawableIdForDice() : Int {
        return when(dice.roll()){

            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}