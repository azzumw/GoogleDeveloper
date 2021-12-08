package com.example.diceroller

class RealDice : Dice{

    internal val sides:Int = 6

    override fun roll() = (1..sides).random()

}