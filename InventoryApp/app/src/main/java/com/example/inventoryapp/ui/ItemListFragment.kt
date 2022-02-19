package com.example.inventoryapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentItemListBinding


class ItemListFragment : Fragment() {

    private var _binding : FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView

        binding.floatingActionButton.setOnClickListener {
            navigateToAddNewItemFragment()
        }
    }

    fun navigateToAddNewItemFragment(){
        findNavController().navigate(R.id.action_itemListFragment_to_addItemFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}