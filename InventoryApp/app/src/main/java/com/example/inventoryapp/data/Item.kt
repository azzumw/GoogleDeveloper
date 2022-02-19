package com.example.inventoryapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    private val id:Int = 0,
    @ColumnInfo(name = "name") private var name:String,
    @ColumnInfo (name = "price")private var price:Double,
    @ColumnInfo (name = "quantity")private var quantity:Int
)
