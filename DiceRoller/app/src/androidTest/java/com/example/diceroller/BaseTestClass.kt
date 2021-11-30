package com.example.diceroller

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

open class BaseTestClass {

    val appContext: Context
    get() = InstrumentationRegistry.getInstrumentation().targetContext
}