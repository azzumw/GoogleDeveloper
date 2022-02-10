package com.example.amphibian.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amphibian.R
import com.example.amphibian.databinding.FragmentAmphibianListBinding


class AmphibianListFragment : Fragment() {

    private val viewModel:AmphibianViewModel by activityViewModels()

    private var _binding:FragmentAmphibianListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAmphibianListBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = AmphibianListAdapter(requireContext(),viewModel.amphibians.value!!)

        recyclerView.setHasFixedSize(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}