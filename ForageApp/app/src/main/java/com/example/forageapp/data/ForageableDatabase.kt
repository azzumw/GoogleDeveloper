package com.example.forageapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forageapp.BaseApplication
import com.example.forageapp.model.Forageable

@Database(entities = [Forageable::class], version = 1, exportSchema = false)
abstract class ForageableDatabase : RoomDatabase(){

    //create an abstract function that returns the Dao
    abstract fun forageableDao(): ForageableDao

    companion object{

        @Volatile
        private var INSTANCE : ForageableDatabase? = null

        fun getDatabase(context: Context) : ForageableDatabase{

            return  INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,ForageableDatabase::class.java,"forageable_database")
                    .fallbackToDestructiveMigration().build()

                INSTANCE = instance

                return instance
            }
        }
    }
}