package com.example.cupcake

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentStartBinding
import com.example.cupcake.viewModel.OrderViewModel

const val TAG_START = "StartFrag"
class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG_START,"onCreate")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(TAG_START,"onCreateView")
        _binding = FragmentStartBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG_START,"onViewCreated")
//        binding.startFragment = this
        binding.apply {
            viewModel = sharedViewModel
            startFragment = this@StartFragment
//            orderOneCupcake.setOnClickListener { orderCupcake(1) }
//            orderSixCupcakes.setOnClickListener { orderCupcake(6) }
//            orderTwelveCupcakes.setOnClickListener { orderCupcake(12) }
        }
    }

    /**
     * Start an order with the desired quantity of cupcakes and navigate to the next screen.
     */
    fun orderCupcake(quantity: Int) {
        sharedViewModel.setQuantity(quantity)

        if(sharedViewModel.hasNoFlavorSet()){
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        val action = StartFragmentDirections.actionStartFragmentToFlavorFragment()

        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG_START,"onDestroyView")
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG_START,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG_START,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG_START,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG_START,"onStop")
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG_START,"onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG_START,"onDetach")
    }

}