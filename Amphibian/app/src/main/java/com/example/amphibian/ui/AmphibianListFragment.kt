package com.example.amphibian.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amphibian.R
import com.example.amphibian.databinding.FragmentAmphibianListBinding


class AmphibianListFragment : Fragment() {

    private val viewModel: AmphibianViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAmphibianListBinding.inflate(inflater)
        binding.apply {
            viewModel = this@AmphibianListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = AmphibianListAdapter(AmphibianListener { amphibian ->
                viewModel?.onAmphibianClicked(amphibian)

                findNavController()
                    .navigate(R.id.action_amphibianListFragment_to_amphibianDetailFragment)
            })

            // Inflate the layout for this fragment
            return binding.root
        }

    }
}
