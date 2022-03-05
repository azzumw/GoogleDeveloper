package com.example.amphibian.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.amphibian.databinding.FragmentAmphibianDetailBinding

class AmphibianDetailFragment : Fragment() {

    private val viewModel:AmphibianViewModel by activityViewModels()

    private var _binding : FragmentAmphibianDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentAmphibianDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

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