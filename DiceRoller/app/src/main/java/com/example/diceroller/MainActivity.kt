package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */

private const val DICE_NUM = "DICE_NUM"
class MainActivity : AppCompatActivity() {

    private var currentNumber = R.drawable.dice_1
    private lateinit var button :Button
    private lateinit var imageView: ImageView
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        imageView = findViewById(R.id.diceImageView)

        mainPresenter = MainPresenter()

        if(savedInstanceState!=null){
            currentNumber = savedInstanceState.getInt(DICE_NUM)
        }

        setImage(currentNumber)

        button.setOnClickListener { rollDice() }
    }

    private fun rollDice(){
        currentNumber = mainPresenter.roll()
        setImage(currentNumber)
    }

    private fun setImage(i : Int){
        imageView.setImageResource(i)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(DICE_NUM,currentNumber)
        super.onSaveInstanceState(outState)
    }
}