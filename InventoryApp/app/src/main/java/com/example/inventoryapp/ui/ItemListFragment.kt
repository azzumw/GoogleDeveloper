package com.example.inventoryapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryapp.InventoryAdapter
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.FragmentItemListBinding
import com.example.inventoryapp.viewmodels.InventoryViewModel
import com.example.inventoryapp.viewmodels.InventoryViewModelFactory


class ItemListFragment : Fragment() {

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory((activity?.application as InventoryApplication).database.itemDao())
    }

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

        val inventoryAdapter = InventoryAdapter {
            val action = ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(it.id)
//
            view.findNavController().navigate(action)
        }

        recyclerView.adapter = inventoryAdapter

        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                inventoryAdapter.submitList(it)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            navigateToAddNewItemFragment()
        }
    }

    private fun navigateToAddNewItemFragment(){
         val action = ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(title = getString(R.string.add_fragment_title))
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}