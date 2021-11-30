package com.example.diceroller

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

@SmallTest
class DiceTest {

    @Test
    fun getSides() {
        assertEquals(6,Dice.sides)
    }

    @Test
    fun checkDiceRangeIsBetween1And6(){
        val random = Dice.getRandom()
        assertThat(random).isIn(1..6)
    }

}