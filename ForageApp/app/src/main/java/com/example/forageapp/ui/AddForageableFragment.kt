package com.example.forageapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.forageapp.BaseApplication
import com.example.forageapp.R
import com.example.forageapp.databinding.FragmentAddForageableBinding
import com.example.forageapp.model.Forageable
import com.example.forageapp.ui.viewmodel.ForageableFactory
import com.example.forageapp.ui.viewmodel.ForageableViewModel

class AddForageableFragment : Fragment() {

    private val viewModel : ForageableViewModel by activityViewModels {
        ForageableFactory((activity?.application as BaseApplication).database.forageableDao())
    }

    private lateinit var forageable : Forageable

    private var _binding:FragmentAddForageableBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddForageableBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveBtn.setOnClickListener{
            addNewForageable()
        }
    }

    private fun isEntryValid():Boolean{
        return viewModel.isValidEntry(
            binding.nameInput.toString(),
            binding.locationAddressInput.toString()
        )
    }

    private fun addNewForageable(){
        if(isEntryValid()){
            viewModel.addForageable(
                binding.nameInput.text.toString(),
                binding.locationAddressInput.text.toString(),
                binding.inSeasonCheckbox.isChecked,
                binding.notesInput.text.toString()
            )

            val action = AddForageableFragmentDirections.actionAddForageableFragmentToForgeableListFragment()
            findNavController().navigate(action)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}