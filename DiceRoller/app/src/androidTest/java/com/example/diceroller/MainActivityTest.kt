package com.example.diceroller

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.diceroller.CustomMatchers.withDrawable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest : BaseTestClass(){

    private val diceImageView = viewWithId(R.id.diceImageView)
    private val rollButton = viewWithId(R.id.button)

    private val fakeDice = FakeDice()

        @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        val applicationContext = appContext.applicationContext

        val diceApplication = applicationContext as DiceApplication

        diceApplication.dice = fakeDice

        launch(MainActivity::class.java)
    }

    @Test
    fun checkImageViewIsDisplayed() {
        diceImageView
            .check(matches(isDisplayed()))
    }

    @Test
    fun check_correct_image_displayed_on_roll() {
        //GIVEN: ROLL NUMBER = 4

        //WHEN: Dice Roll button is clicked
        rollButton.perform(click())

        //THEN: image for dice 4 is displayed
        diceImageView.check(matches(withDrawable(R.drawable.dice_4)))
    }

    @Test
    fun test_correct_default_image_is_set(){
        diceImageView.check(matches(withDrawable(R.drawable.dice_1)))
    }

}