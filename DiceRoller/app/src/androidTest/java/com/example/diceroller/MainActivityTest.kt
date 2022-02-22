package com.example.diceroller

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.diceroller.CustomMatchers.withDrawable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : BaseTestClass(){

    private val diceImageView = viewWithId(R.id.diceImageView)
    private val rollButton = viewWithId(R.id.button)

    private val fakeDice = FakeDice()

    @Before
    fun setUp() {
        val applicationContext = appContext.applicationContext

        val diceApplication = applicationContext as DiceApplication

        diceApplication.dice = fakeDice

        launch(MainActivity::class.java)
    }

    @Test
    fun preconditions() {
        diceImageView.check(matches(isDisplayed()))

        diceImageView.check(matches(withDrawable(R.drawable.dice_1)))
    }

    @Test
    fun clicking_the_roll_button_rolls_the_dice_and_shows_the_expected_number() {
        //GIVEN:
        fakeDice.number = 4

        //WHEN:
        rollButton.perform(click())

        //THEN:
        diceImageView.check(matches(withDrawable(R.drawable.dice_4)))
    }
}