package com.example.lunchtray.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentAccompanimentMenuBinding
import com.example.lunchtray.model.OrderViewModel


class AccompanimentMenuFragment : Fragment() {

    private var _binding : FragmentAccompanimentMenuBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=  FragmentAccompanimentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            accompanimentFragment = this@AccompanimentMenuFragment
            viewModel = sharedViewModel
            nextButton.setOnClickListener {
                gotoNextScreen()
            }

            cancelButton.setOnClickListener {
                sharedViewModel.resetOrder()
            }
        }
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_accompanimentMenuFragment_to_startOrderFragment)
    }

    fun gotoNextScreen(){
       findNavController().navigate(R.id.action_accompanimentMenuFragment_to_checkoutFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}