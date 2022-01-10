package com.example.cupcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.cupcake.databinding.FragmentSummaryBinding
import com.example.cupcake.viewModel.OrderViewModel

class SummaryFragment : Fragment() {

    private var _binding : FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSummaryBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            summaryFragment = this@SummaryFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner

        }
    }

    fun sendOrder(){
        Toast.makeText(context,"Order submitted. hooray!",Toast.LENGTH_SHORT).show()
    }
}