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
//    TODO(2): addtestCorrectImageShown

    //    @get:Rule
//    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun setUp() {
        launch(MainActivity::class.java)
    }

    @Test
    fun checkTextViewIsEmpty() {
        diceImageView
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickRollButton() {
        rollButton.perform(click())
    }

    @Test
    fun test_correctImageResIdShown(){
        diceImageView.check(matches(withDrawable(R.drawable.dice_1)))
    }

}