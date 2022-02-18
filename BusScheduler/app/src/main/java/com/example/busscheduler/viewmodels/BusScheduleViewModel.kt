package com.example.busscheduler.viewmodels

import androidx.lifecycle.ViewModel
import com.example.busscheduler.database.schedule.Schedule
import com.example.busscheduler.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val dao: ScheduleDao):ViewModel() {

    fun fullSchedule() : Flow<List<Schedule>> = dao.getAll()

    fun scheduleForStopName(name:String) : Flow<List<Schedule>> = dao.getByStopName(name)
}