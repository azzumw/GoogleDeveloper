package com.example.diceroller

import org.junit.Assert.assertEquals
import org.junit.Test

class MainPresenterTestWithFakes {

    private val dice = FakeDice()
    private val mainPresenter = MainPresenter(dice)


    @Test
    fun getDrawableIdForDice() {
        //given
        dice.number = 3
        //when
        val drawableID = mainPresenter.getDrawableIdForDice()
        //then
        assertEquals(R.drawable.dice_3,drawableID)
    }
}