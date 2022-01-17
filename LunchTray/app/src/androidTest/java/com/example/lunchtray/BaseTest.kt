package com.example.lunchtray

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class BaseTest {

    val context =  InstrumentationRegistry.getInstrumentation().targetContext

    fun fullOrderFlow() {
        // Launch the main activity
        launch(MainActivity::class.java)

        // Start order
        onView(withId(R.id.startOrderBtn)).perform(click())
        // Select entree item
        onView(withId(R.id.cauliflower)).perform(click())
        // Move to next fragment
        onView(withId(R.id.next_button)).perform(click())
        // Select side item
        onView(withId(R.id.salad)).perform(click())
        // Move to next fragment
        onView(withId(R.id.next_button)).perform(click())
        // Select accompaniment item
        onView(withId(R.id.bread)).perform(click())
        // Move to next fragment
        onView(withId(R.id.next_button)).perform(click())
    }
}