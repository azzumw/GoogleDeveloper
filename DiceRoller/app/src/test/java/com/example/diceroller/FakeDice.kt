package com.example.diceroller

class FakeDice : Dice{
    var number = 4

    override fun roll(): Int {
        return number
    }
}