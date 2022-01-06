package com.example.unscramble.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unscramble.R
import com.example.unscramble.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private var score = 0
    private var currentWordCount = 0
    private var currentScrambledWord = "test"

    private lateinit var binding : FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Update the UI
        updateNextWordOnScreen()

        binding.score.text = getString(R.string.score, 0)
        binding.wordCount.text = getString(
            R.string.word_count, 0, MAX_NO_OF_WORDS)

        //set up click listeners
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }
    }

    /*
    * Checks the user's word, and updates the score accordingly.
    * Displays the next scrambled word.
    */
    private fun onSubmitWord(){
        currentScrambledWord = getNextScrambledWord()
        currentWordCount++
        score+= SCORE_INCREASE
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        binding.score.text = getString(R.string.score, score)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }


    /*
     * Skips the current word without changing the score.
     * Increases the word count.
     */
    private fun onSkipWord(){
        currentScrambledWord = getNextScrambledWord()
        currentWordCount++
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    /*
    * Gets a random word from the list of words and shuffles the letters in it.
    */
    private fun getNextScrambledWord(): String{
        val tempWord = allWordsList.random().toCharArray()
        tempWord.shuffle()
        return String(tempWord)
    }

    /*
    * Displays the next scrambled word on screen.
    */
    private fun updateNextWordOnScreen(){
        binding.textViewUnscrambledWord.text = currentScrambledWord
    }

    private fun setErrorTextField(error:Boolean){
        if(error){
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        }else{
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }

    private fun exitGame(){
        activity?.finish()
    }

    /*
   * Re-initializes the data in the ViewModel and updates the views with the new data, to
   * restart the game.
   */
    private fun restartGame() {
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

}