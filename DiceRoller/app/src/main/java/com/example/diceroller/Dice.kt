package com.example.diceroller

class Dice{

    internal val sides:Int = 6

    fun getRandom() = (1..sides).random()

}