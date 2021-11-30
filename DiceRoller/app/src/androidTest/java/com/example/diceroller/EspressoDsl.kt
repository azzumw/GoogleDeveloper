package com.example.diceroller

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId


fun viewWithId(id:Int) :ViewInteraction =
    Espresso.onView(withId(id))