package com.example.busscheduler.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    //Kotlin feature called asynchronous flow (often just called flow)
    // that will allow the DAO to continuously emit data from the database
    @Query("select * from schedule order by arrival_time")
    fun getAll(): Flow<List<Schedule>>

    @Query("select * from schedule where stop_name = :stopName order by arrival_time")
    fun getByStopName(stopName:String): Flow<List<Schedule>>
}