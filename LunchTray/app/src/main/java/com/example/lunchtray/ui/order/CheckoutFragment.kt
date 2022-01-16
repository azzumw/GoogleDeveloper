package com.example.lunchtray.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentCheckoutBinding
import com.example.lunchtray.model.OrderViewModel

class CheckoutFragment : Fragment() {

    private var _binding : FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCheckoutBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            checkoutFragment = this@CheckoutFragment
            viewModel = sharedViewModel

        }
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        //navigate to StartFragment
        findNavController().navigate(R.id.action_checkoutFragment_to_startOrderFragment)
    }

    fun submitOrder(){
        Toast.makeText(context,getString(R.string.submit_order),Toast.LENGTH_SHORT).show()
        cancelOrder()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}