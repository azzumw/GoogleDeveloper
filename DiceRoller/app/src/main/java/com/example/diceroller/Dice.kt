package com.example.diceroller

class Dice {
    private val _sides : Int
    get() = 6
    val sides = _sides

    fun roll() : Int{
        return (1.._sides).random()
    }
}