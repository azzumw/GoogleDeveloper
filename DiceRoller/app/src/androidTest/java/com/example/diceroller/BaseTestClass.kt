package com.example.diceroller

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
open class BaseTestClass {

    lateinit var appContext: Context



    @Before
    fun useAppContext() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        Assert.assertEquals("com.example.diceroller", appContext.packageName)
    }
}