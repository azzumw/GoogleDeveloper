package com.example.diceroller

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test


class MainActivityTest : BaseTestClass(){

//    TODO (1): add testImageResourceID
//    TODO(2): addtestCorrectImageShown

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkTextViewIsEmpty() {
//        launch(MainActivity::class.java)
        onView(withId(R.id.diceImageView))
            .check(matches(withContentDescription(R.string.dice_image_text)))
    }

    @Test
    fun clickRollButton() {
        launch(MainActivity::class.java)
        onView(withId(R.id.button)).perform(click())
    }

}