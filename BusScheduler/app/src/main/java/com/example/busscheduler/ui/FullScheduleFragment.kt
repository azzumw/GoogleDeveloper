package com.example.busscheduler.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busscheduler.adapter.BusStopAdapter
import com.example.busscheduler.databinding.FragmentFullScheduleBinding
import com.example.busscheduler.viewmodels.BusScheduleViewModel
import com.example.busscheduler.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [FullScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullScheduleFragment : Fragment() {

    private val viewModel : BusScheduleViewModel by activityViewModels{
        BusScheduleViewModelFactory((
            activity?.application as BusScheduleApplication).database.scheduleDao())
    }

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

        val busStopAdapter = BusStopAdapter {
            val action =
                FullScheduleFragmentDirections.actionFullScheduleFragmentToStopScheduleFragment(
                    stopName = it.stopName
                )

            view.findNavController().navigate(action)
        }

        recyclerView.adapter = busStopAdapter


        // submitList() is a call that accesses the database. To prevent the
        // call from potentially locking the UI, you should use a
        // coroutine scope to launch the function. Using GlobalScope is not
        // best practice, and in the next step we'll see how to improve this.
        GlobalScope.launch(Dispatchers.IO) {
            busStopAdapter.submitList(viewModel.fullSchedule())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}