package com.example.diceroller

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@SmallTest
class DiceTest {
    private var dice: Dice? = null

    @Before
    fun setup(){
        dice = Dice()
    }

    @Test
    fun getSides() {
        assertEquals(6,dice!!.sides)
    }

    @Test
    fun roll(){
        val random = dice!!.roll()
        assertThat(random).isIn(1..6)
    }

    @After
    fun tearDown(){
        dice = null
    }
}