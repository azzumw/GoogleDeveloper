package com.example.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel


private const val TAG = "VIEW_MODEL"
class GameViewModel : ViewModel() {

    private lateinit var currentWord:String
    private var wordsList:MutableList<String> = mutableListOf()

    //backing property
    //inside the class it should be editable and private
    //outside the class it should only be readable and public
    private var _score = 0
    //For getter and setter methods, you could override one or both of these methods
    // and provide your own custom behavior. To implement a backing property,
    // you will override the getter method to return a read-only version of your data
    val score :Int
    get() = _score

    private var _currentWordCount = 0
    val currentWordCount : Int
    get() = _currentWordCount

    private lateinit var _currentScrambledWord : String
    val currentScrambledWord : String
    get() = _currentScrambledWord

    init {
        Log.e(TAG, "GameViewModel created!")
        nextWord()
//        getNextWord()
    }

    fun getNextWord(){
        currentWord = allWordsList.random()
        var tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        if(wordsList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambledWord = String((tempWord))
            _currentWordCount++
            wordsList.add(currentWord)
        }
    }

    /**
* Returns true if the current word count is less than MAX_NO_OF_WORDS.
* Updates the next word.
*/
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun reInitialiseData(){
        _currentWordCount = 0
        _score = 0
        wordsList.clear()
        nextWord()
    }


    override fun onCleared() {
        super.onCleared()
        Log.e(TAG,"view model destroyed")
    }

}