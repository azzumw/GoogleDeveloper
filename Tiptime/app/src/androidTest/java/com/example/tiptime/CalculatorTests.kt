package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_default_tip() {
        //given
        onView(withId(R.id.cost_of_service)).perform(typeText("50.00"))

        //when
        onView(withId(R.id.calculate_button)).perform(click())

        //then
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$10.00"))))

    }

    @Test
    fun calculate_good_tip() {

        //Given.
        onView(withId(R.id.cost_of_service)).perform(typeText("50.00"))

        //When.
        onView(withId(R.id.option_eighteen_percent)).perform(click()).check(matches(isChecked()))


        onView(withId(R.id.calculate_button)).perform(click())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val amount = context.getString(R.string.tip_amount,"$9.00")

        //Then.
        onView(withId(R.id.tip_result)).check(matches(withText(amount)))

    }

    @Test
    fun calculate_defaultTip_uponNoTextEntered(){
        onView(withId(R.id.calculate_button)).perform(click())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val amount = context.getString(R.string.tip_amount,"$0.00")

        onView(withId(R.id.tip_result)).check(matches(withText(amount)))
    }

}