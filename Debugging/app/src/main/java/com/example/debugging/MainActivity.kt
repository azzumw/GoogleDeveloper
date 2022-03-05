package com.example.debugging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

/**
 * In summary:

You can set breakpoints to pause the execution of your app.
When execution is paused, you can "step over" to execute only a single line of code.
You can set conditional statements to only trigger breakpoints based on a Kotlin expression.
Watches allow you to group variables of interest when debugging.
 */

val TAG = "MainActivity"
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        division()
    }

    fun division() {
        val numerator = 60
        var denominator = 4
        repeat(5) {
            Log.v(TAG, "${numerator / denominator}")
            denominator--
        }
    }
}