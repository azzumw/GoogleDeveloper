package com.example.cupcake

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentFlavorBinding
import com.example.cupcake.viewModel.OrderViewModel

const val TAG_FLAV = "Flavour"
class FlavorFragment : Fragment() {

    private var _binding : FragmentFlavorBinding? = null
    private val binding  get() = _binding!!

    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG_FLAV,"onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e(TAG_FLAV,"onCreateView")

        _binding = FragmentFlavorBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG_FLAV,"onViewCreated")
        binding.apply {
            flavorFragment = this@FlavorFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun goToNextScreen(){

        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG_FLAV,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG_FLAV,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG_FLAV,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG_FLAV,"onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG_FLAV,"onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG_FLAV,"onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG_FLAV,"onDetach")
    }


}