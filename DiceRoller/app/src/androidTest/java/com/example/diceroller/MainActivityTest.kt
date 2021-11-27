package com.example.diceroller

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test


class MainActivityTest : BaseTestClass(){


    @Rule @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)
//    @get:Rule
//    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkTextViewIsEmpty() {

        onView(withId(R.id.textView))
            .check(matches(withText("")))
    }

    @Test
    fun clickRollButton() {
        onView(withId(R.id.button)).perform(click())
    }
}