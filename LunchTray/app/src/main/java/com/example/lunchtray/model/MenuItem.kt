package com.example.lunchtray.model

import java.text.NumberFormat

data class MenuItem (val name:String, val description:String,val price:Double,val type:Int){
    fun getFormattedPrice():String{
        return NumberFormat.getCurrencyInstance().format(price)
    }
}