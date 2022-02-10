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


class AmphibianDetailFragment : Fragment() {

    private val viewModel:AmphibianViewModel by activityViewModels()

    private var _binding : FragmentAmphibianDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAmphibianDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.text = viewModel.singleAmphibian.value!!.name
        binding.description.text = viewModel.singleAmphibian.value!!.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}