package com.example.forageapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forageable")
data class Forageable(
    @PrimaryKey (autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val address:String,
    @ColumnInfo(name ="in_season")
    val inSeason: Boolean,
    val notes:String?
)
