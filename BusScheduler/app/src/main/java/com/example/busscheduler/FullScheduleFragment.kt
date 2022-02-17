package com.example.busscheduler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busscheduler.databinding.FragmentFullScheduleBinding


/**
 * A simple [Fragment] subclass.
 * Use the [FullScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullScheduleFragment : Fragment() {

    private var _binding : FragmentFullScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding  = FragmentFullScheduleBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}