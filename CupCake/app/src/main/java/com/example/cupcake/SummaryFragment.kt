package com.example.cupcake

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        val numberOfCupcakes = sharedViewModel.quantity.value ?: 0

        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.value.toString(),
            sharedViewModel.price.value.toString())

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            putExtra(Intent.EXTRA_TEXT, orderSummary)
        }

        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }

        Toast.makeText(context,"Order submitted. hooray!",Toast.LENGTH_SHORT).show()
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }
}