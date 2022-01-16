package com.example.lunchtray.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentSideMenuBinding
import com.example.lunchtray.model.OrderViewModel

class SideMenuFragment : Fragment() {
    private var _binding : FragmentSideMenuBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : OrderViewModel  by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideMenuBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            gotoNextScreen()
        }
    }

    fun gotoNextScreen(){
        findNavController().navigate(R.id.action_sideMenuFragment2_to_accompanimentMenuFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}