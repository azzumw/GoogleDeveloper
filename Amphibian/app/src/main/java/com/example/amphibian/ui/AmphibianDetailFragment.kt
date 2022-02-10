package com.example.amphibian.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.amphibian.R
import com.example.amphibian.databinding.FragmentAmphibianDetailBinding
import kotlin.properties.Delegates


const val KEY_POS = "pos"
class AmphibianDetailFragment : Fragment() {

    private val viewModel:AmphibianViewModel by activityViewModels()

    private var _binding : FragmentAmphibianDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var pos : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            pos = it?.getInt(KEY_POS).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAmphibianDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}