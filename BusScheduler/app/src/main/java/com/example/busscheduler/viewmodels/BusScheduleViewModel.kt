package com.example.busscheduler.viewmodels

import androidx.lifecycle.ViewModel
import com.example.busscheduler.database.schedule.Schedule
import com.example.busscheduler.database.schedule.ScheduleDao

class BusScheduleViewModel(private val dao: ScheduleDao):ViewModel() {

    fun fullSchedule() : List<Schedule> = dao.getAll()

    fun scheduleForStopName(name:String) : List<Schedule> = dao.getByStopName(name)
}