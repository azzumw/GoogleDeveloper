package com.example.cupcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentFlavorBinding
import com.example.cupcake.viewModel.OrderViewModel

class FlavorFragment : Fragment() {

    private var _binding : FragmentFlavorBinding? = null
    private val binding  get() = _binding!!

    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFlavorBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            flavorFragment = this@FlavorFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun goToNextScreen(){

        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }


}