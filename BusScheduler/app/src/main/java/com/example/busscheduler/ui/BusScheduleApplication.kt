package com.example.busscheduler.ui

import android.app.Application
import com.example.busscheduler.database.schedule.AppDatabase

class BusScheduleApplication:Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

}