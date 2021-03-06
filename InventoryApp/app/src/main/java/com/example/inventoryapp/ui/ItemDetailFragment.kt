package com.example.inventoryapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.inventoryapp.R
import com.example.inventoryapp.data.Item
import com.example.inventoryapp.data.getFormattedPrice
import com.example.inventoryapp.databinding.FragmentItemDetailBinding
import com.example.inventoryapp.viewmodels.InventoryViewModel
import com.example.inventoryapp.viewmodels.InventoryViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collect


class ItemDetailFragment : Fragment() {

    private var _binding : FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: ItemDetailFragmentArgs by navArgs()

    lateinit var item: Item

    private val viewModel : InventoryViewModel by activityViewModels{
        InventoryViewModelFactory((activity?.application as InventoryApplication).database.itemDao())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = navigationArgs.id


        viewModel.getItem(itemId).observe(this.viewLifecycleOwner) {
            item = it
            bind(item)
        }
    }

    private fun bind(item: Item) {
        binding.apply {
            itemName.text = item.name
            itemPrice.text = item.getFormattedPrice()
            itemCount.text = item.quantity.toString()

            sellItem.isEnabled = viewModel.isStockAvailable(item)
            sellItem.setOnClickListener {
                viewModel.sellItem(item)
            }

            deleteItem.setOnClickListener {
                showConfirmationDialog()
            }

            editItem.setOnClickListener {
                editItem()
            }
        }
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }

    private fun deleteItem() {
        viewModel.deleteItem(item)
        findNavController().navigateUp()
    }

    private fun editItem() {
        val action = ItemDetailFragmentDirections.actionItemDetailFragmentToAddItemFragment(
            getString(R.string.edit_fragment_title),
            item.id
        )
        this.findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}