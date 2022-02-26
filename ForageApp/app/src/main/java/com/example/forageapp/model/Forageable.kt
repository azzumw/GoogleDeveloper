package com.example.forageapp.model

data class Forageable(
    val id: Long = 0,
    val name: String,
    val address:String,
    val inSeason: Boolean,
    val notes:String?
)
