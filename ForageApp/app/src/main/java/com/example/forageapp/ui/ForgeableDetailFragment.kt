package com.example.forageapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forageapp.BaseApplication
import com.example.forageapp.R
import com.example.forageapp.databinding.FragmentForgeableDetailBinding
import com.example.forageapp.model.Forageable
import com.example.forageapp.ui.viewmodel.ForageableFactory
import com.example.forageapp.ui.viewmodel.ForageableViewModel


class ForgeableDetailFragment : Fragment() {

    private val navigationArgs : ForgeableDetailFragmentArgs by navArgs()

    private var _binding : FragmentForgeableDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var forageable:Forageable

    private val viewModel : ForageableViewModel by activityViewModels{
        ForageableFactory((activity?.application as BaseApplication).database.forageableDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgeableDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.id

        viewModel.getAForageable(id).observe(viewLifecycleOwner){
            forageable = it
            bind(forageable)
        }

        binding.editForageableFab.setOnClickListener {
            val action = ForgeableDetailFragmentDirections.actionForgeableDetailFragmentToAddForageableFragment(title = getString(R.string.edit_fragment_label),forageable.id)
            findNavController().navigate(action)
        }
    }

    private fun bind(forageable: Forageable){
        binding.name.text = forageable.name
        binding.location.text = forageable.address
        binding.season.text = forageable.inSeason.toString()
        binding.notes.text = forageable.notes
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}