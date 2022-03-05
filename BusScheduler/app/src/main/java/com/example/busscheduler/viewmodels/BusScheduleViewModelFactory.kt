package com.example.busscheduler.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busscheduler.database.schedule.ScheduleDao

class BusScheduleViewModelFactory(private val dao:ScheduleDao)
    : ViewModelProvider.Factory {

    //You can now instantiate a BusScheduleViewModelFactory
    // object with BusScheduleViewModelFactory.create(),
    // so that your view model can be lifecycle aware without
    // your fragment having to handle this directly.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(BusScheduleViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}