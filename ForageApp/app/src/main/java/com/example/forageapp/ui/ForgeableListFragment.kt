package com.example.forageapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.forageapp.BaseApplication
import com.example.forageapp.R
import com.example.forageapp.databinding.FragmentForgeableListBinding
import com.example.forageapp.ui.adapter.ForageableListAdapter
import com.example.forageapp.ui.viewmodel.ForageableFactory
import com.example.forageapp.ui.viewmodel.ForageableViewModel

class ForgeableListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val viewModel : ForageableViewModel by activityViewModels {
        ForageableFactory((activity?.application as BaseApplication).database.forageableDao())
    }
    private var _binding : FragmentForgeableListBinding? = null
    private val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgeableListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView

        val adapter = ForageableListAdapter {
            val action = ForgeableListFragmentDirections.actionForgeableListFragmentToForgeableDetailFragment(name = it.name, id = it.id)
            findNavController().navigate(action)
        }

        recyclerView.adapter = adapter

        viewModel.forageables.observe(viewLifecycleOwner){
            it.let {
                adapter.submitList(it)
            }
        }

        binding.addForageableFab.setOnClickListener {

            val action = ForgeableListFragmentDirections.actionForgeableListFragmentToAddForageableFragment(title = getString(R.string.add_fragment_label))

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}