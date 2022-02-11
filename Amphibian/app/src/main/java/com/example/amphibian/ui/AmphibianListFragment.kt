package com.example.amphibian.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amphibian.R
import com.example.amphibian.databinding.FragmentAmphibianListBinding

class AmphibianListFragment : Fragment() {

    private val viewModel: AmphibianViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentAmphibianListBinding.inflate(inflater)

        binding.apply {
            viewModel = this@AmphibianListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = AmphibianListAdapter(AmphibianListener { amphibian ->
                viewModel?.onAmphibianClicked(amphibian)
                val title = this@AmphibianListFragment.viewModel.singleAmphibian.value!!.name

//                val bundle = Bundle()
//                bundle.putString("title",title)
                findNavController().navigate(R.id.action_amphibianListFragment_to_amphibianDetailFragment)
            })

            return binding.root
        }
    }
}
