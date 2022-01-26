package com.example.lunchtray.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentEntreeMenuBinding
import com.example.lunchtray.model.OrderViewModel


class EntreeMenuFragment : Fragment() {
    private var _binding : FragmentEntreeMenuBinding? = null
    val binding get() = _binding!!

    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEntreeMenuBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            entreeFragment = this@EntreeMenuFragment
            viewModel = sharedViewModel
        }
    }

    fun gotoNextScreen(){
        val action = EntreeMenuFragmentDirections.actionEntreeMenuFragmentToSideMenuFragment2()
        findNavController().navigate(action)
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_entreeMenuFragment_to_startOrderFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}