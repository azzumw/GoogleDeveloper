package com.example.watermeproject

import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NotificationTest {

        private val timeout: Long = 6000

        private val plantName = "Carrot"

        @get:Rule
        var activityRule: ActivityScenarioRule<MainActivity>
                = ActivityScenarioRule(MainActivity::class.java)

        private lateinit var uiDevice: UiDevice

        @Before
        fun setup() {
            uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            Espresso.onView(ViewMatchers.withText("Carrot")).perform(ViewActions.longClick())
            Espresso.onView(ViewMatchers.withText("5 seconds")).perform(ViewActions.click())
            uiDevice.pressHome()
            uiDevice.openNotification()
            uiDevice.wait(Until.hasObject(By.textContains(plantName)), timeout)
        }

        @Test
        fun notification_scheduled() {
            val notification = uiDevice.findObject(UiSelector().textContains(plantName)).exists()
            Assert.assertTrue("Could not find text 'Carrot'", notification)
            uiDevice.pressHome()
        }

        @Test
        fun notification_click() {
            val notification = uiDevice.findObject(UiSelector().textContains("Carrot"))
            notification.click()
            uiDevice.wait(Until.hasObject(By.pkg("com.example.waterme")
                .depth(0)), 1000)
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val packageName = context.packageName
            Log.e("TEST",packageName)
            val pkg = uiDevice.findObject(UiSelector().packageName(packageName))
                .exists()
            Assert.assertTrue("Could not find package", pkg)
        }
}
