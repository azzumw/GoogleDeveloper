package com.example.wordstage2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordstage2.databinding.FragmentWordListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [WordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordListFragment : Fragment() {

    private var _binding : FragmentWordListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWordListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val letterId = activity?.intent?.extras?.get(KEY_LETTER).toString()

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WordAdapter(letterId,requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        activity?.title = getString(R.string.detail_prefix,letterId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}