package com.example.unscramble.ui.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.unscramble.R
import com.example.unscramble.databinding.FragmentGameBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "GAME_FRAGMENT"
class GameFragment : Fragment() {

    //Property delegation in Kotlin helps you to handoff the getter-setter responsibility to a different class
    //This class (called delegate class) provides getter and setter functions of the property and handles its changes.
    //we get the same viewModel instance because it is retained.
    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding : FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(TAG,"OnCreateView - fragment created/recreated")
//        binding = FragmentGameBinding.inflate(inflater,container,false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game,container,false)

        Log.d(
            TAG, "Word: ${viewModel.currentScrambledWord} " +
                "Score: ${viewModel.score} WordCount: ${viewModel.currentWordCount}")
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"OnViewCreated")
        // Update the UI

        binding.gameViewModel = viewModel
        binding.maxNoOfWords = MAX_NO_OF_WORDS

        binding.lifecycleOwner = viewLifecycleOwner
        //set up click listeners
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }
    }

    override fun onStart() {
        super.onStart()
        //This parameter helps the LiveData to be aware of the GameFragment
        // lifecycle and notify the observer only when the GameFragment is
        // in active states (STARTED or RESUMED).
//        viewModel.currentScrambledWord.observe(viewLifecycleOwner,
//            {it -> binding.textViewUnscrambledWord.text = it } )

//        viewModel.score.observe(viewLifecycleOwner,{
//            newScore ->  binding.score.text = getString(R.string.score,newScore)
//        })

//        viewModel.currentWordCount.observe(viewLifecycleOwner,{
//            newWordCount -> binding.wordCount.text =
//            getString(R.string.word_count,newWordCount, MAX_NO_OF_WORDS)
//        })

    }

    /**
    * Checks the user's word, and updates the score accordingly.
    * Displays the next scrambled word.
    */
    private fun onSubmitWord(){
        val userInput = binding.textInputEditText.text.toString()
        if(viewModel.isUserWordCorrect(userInput)){
            setErrorTextField(false)
            if(!viewModel.nextWord()){
                showFinalScoreDialog()
            }
        }else{
            setErrorTextField(true)
        }

    }


    /**
     * Skips the current word without changing the score.
     * Increases the word count.
     */
    private fun onSkipWord(){
        if(viewModel.nextWord()){
            setErrorTextField(false)

        }else{
            showFinalScoreDialog()
        }
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

    fun showFinalScoreDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored,viewModel.score.value))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)){_, _ ->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)){_,_ ->
                restartGame()
            }
            .show()
    }


    private fun exitGame(){
        activity?.finish()
    }

    /**
   * Re-initializes the data in the ViewModel and updates the views with the new data, to
   * restart the game.
   */
    private fun restartGame() {
        setErrorTextField(false)
        viewModel.reInitialiseData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG,"DestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"OnDestroy")
    }
    override fun onDetach() {
        super.onDetach()
        Log.e(TAG,"OnDetach()")
    }

}