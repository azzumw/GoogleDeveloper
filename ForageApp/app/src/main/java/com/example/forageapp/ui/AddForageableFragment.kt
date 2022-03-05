package com.example.forageapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forageapp.BaseApplication
import com.example.forageapp.databinding.FragmentAddForageableBinding
import com.example.forageapp.model.Forageable
import com.example.forageapp.ui.viewmodel.ForageableFactory
import com.example.forageapp.ui.viewmodel.ForageableViewModel

class AddForageableFragment : Fragment() {
    private val navArgs : AddForageableFragmentArgs by navArgs()

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

        val id = navArgs.id

        if(id > 0){

            viewModel.getAForageable(id).observe(viewLifecycleOwner){
                forageable = it
                bind(forageable)
            }

        }else{
            binding.saveBtn.setOnClickListener{
                addNewForageable()
            }
        }
    }

    private fun bind(forageable: Forageable){
        binding.apply {
            nameInput.setText(forageable.name,TextView.BufferType.SPANNABLE)
            locationAddressInput.setText(forageable.address,TextView.BufferType.SPANNABLE)
            inSeasonCheckbox.isChecked = forageable.inSeason
            notesInput.setText(forageable.notes,TextView.BufferType.SPANNABLE)
            deleteBtn.visibility = View.VISIBLE
            saveBtn.setOnClickListener{
                updateForageable()
            }
            deleteBtn.setOnClickListener {
                viewModel.deleteForageable(forageable)
                navigateToForageListFragment()
            }
        }
    }

    private fun isEntryValid():Boolean{
        return viewModel.isValidEntry(
            binding.nameInput.toString(),
            binding.locationAddressInput.toString()
        )
    }

    private fun updateForageable(){
        if (isEntryValid()){
            viewModel.updateForageable(
                navArgs.id,
                binding.nameInput.text.toString(),
                binding.locationAddressInput.text.toString(),
                binding.inSeasonCheckbox.isChecked,
                binding.notesInput.text.toString()
            )
            navigateToForageListFragment()
        }
    }

    private fun navigateToForageListFragment(){
        val action = AddForageableFragmentDirections.actionAddForageableFragmentToForgeableListFragment()
        findNavController().navigate(action)
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