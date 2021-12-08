package com.example.diceroller

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@SmallTest
class DiceTest {
<<<<<<< Updated upstream
    private lateinit var dice:Dice
=======
    private lateinit var dice: RealDice
>>>>>>> Stashed changes

    @Before
    fun setUp(){
        dice = RealDice()
    }
    @Test
    fun getSides() {
        assertEquals(6,dice.sides)
    }

    @Test
    fun checkDiceRangeIsBetween1And6(){
        val random = dice.roll()
        assertThat(random).isIn(1..6)
    }

}