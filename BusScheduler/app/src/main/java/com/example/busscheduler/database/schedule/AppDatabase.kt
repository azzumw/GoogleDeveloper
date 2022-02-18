package com.example.busscheduler.database.schedule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Schedule::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    //Specify which entities are defined in the database.
    //Provide access to a single instance of each DAO class.
    //Perform any additional setup, such as pre-populating the database.

    abstract fun scheduleDao() : ScheduleDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context:Context):AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context,AppDatabase::class.java,"app_database")
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}

