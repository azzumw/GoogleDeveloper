package com.example.cupcake.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel:ViewModel() {

    private val _quantity = MutableLiveData(0)
    val quantity : LiveData<Int> = _quantity

    private val _flavor = MutableLiveData("")
    val flavor : LiveData<String> = _flavor

    private val _date = MutableLiveData("")
    val date : LiveData<String> = _date

    private val _price = MutableLiveData(0.0)
    val price : LiveData<Double> = _price

    fun setQuantity(numberOfCupCakes : Int){}

    fun setFlavor(desiredFlavor : String){}

    fun setDate(pickupDate:String){}
}