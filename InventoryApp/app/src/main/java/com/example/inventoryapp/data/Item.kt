package com.example.inventoryapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo(name = "name")  val name:String,
    @ColumnInfo (name = "price") val price:Double,
    @ColumnInfo (name = "quantity") val quantity:Int
)

fun Item.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(price)
