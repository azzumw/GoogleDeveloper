package com.example.diceroller

object Dice{

    internal const val sides:Int = 6


    fun getRandom() = (1..sides).random()

}