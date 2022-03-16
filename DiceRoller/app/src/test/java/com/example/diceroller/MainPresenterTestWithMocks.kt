package com.example.diceroller

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTestWithMocks {

    @Mock
    private lateinit var dice : Dice

    private lateinit var mainPresenter : MainPresenter

    @Before
    fun setUp() {
        mainPresenter = MainPresenter(dice)
    }

    @Test
    fun getDrawableIdForDice() {
        //given
        `when`(dice.roll()).thenReturn(3)
        //when
        val drawableID = mainPresenter.getDrawableIdForDice()
        //then
        Assert.assertEquals(R.drawable.dice_3, drawableID)
    }
}