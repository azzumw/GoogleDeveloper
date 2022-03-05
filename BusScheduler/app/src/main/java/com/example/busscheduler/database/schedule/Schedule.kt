package com.example.busscheduler.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//to optionally define table name: @Entity(tableName="schedule")
@Entity
data class Schedule (
    @PrimaryKey val id:Int,
    @NonNull @ColumnInfo(name = "stop_name")val stopName : String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime:Int
    )