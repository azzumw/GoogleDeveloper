package com.example.lunchtray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.DataSource
import java.text.NumberFormat

class OrderViewModel: ViewModel() {
    // Map of menu items
    val menuItems = DataSource.menuItems

    // Default values for item prices
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    // Side for the order
    private val _side = MutableLiveData<MenuItem?>()
    val side : LiveData<MenuItem?> = _side

    //Accompaniment for the order
    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment : LiveData<MenuItem?> = _accompaniment

    private var _subtotal = MutableLiveData(0.0)
    val subtotal : LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private var _total = MutableLiveData(0.0)
    val total : LiveData<String> = Transformations.map(_total){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private var _tax = MutableLiveData(0.0)
    val tax : LiveData<String> = Transformations.map(_tax){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val taxRate = 0.08

    fun setEntree(mEntree : String){
        if(_entree.value != null){
            previousEntreePrice = _entree.value!!.price

        }
        if (_subtotal.value != null){
            _subtotal.value = _subtotal.value?.minus(previousEntreePrice)
        }

        _entree.value = menuItems[mEntree]

        updateSubtotal(entree.value!!.price)

    }

    /**
     * Set the side for the order.
     */
    fun setSide(side: String) {
        if (_side.value != null) {
             previousSidePrice = _side.value!!.price
        }

        if(_subtotal.value != null){
            _subtotal.value = _subtotal.value?.minus(previousSidePrice)
        }
        //  subtotal value. This ensures that we only charge for the currently selected side.

        _side.value = menuItems[side]
        updateSubtotal(_side.value!!.price)
    }

    /**
     * Set the accompaniment for the order.
     */
    fun setAccompaniment(accompaniment: String) {

        if(_accompaniment.value != null){
            previousAccompanimentPrice = _accompaniment.value!!.price
        }

        if(_subtotal.value != null){
            _subtotal.value = _subtotal.value?.minus(previousSidePrice)
        }


        _accompaniment.value = menuItems[accompaniment]
        updateSubtotal(_accompaniment.value!!.price)
    }


    /**
     * Update subtotal value.
     */
    private fun updateSubtotal(itemPrice: Double) {

        if(_subtotal.value!=null){
            _subtotal.value = _subtotal.value!!.plus(itemPrice)
        }else{
            _subtotal.value = itemPrice
        }
        calculateTaxAndTotal()
    }

    /**
     * Calculate tax and update total.
     */
    fun calculateTaxAndTotal() {
        _tax.value = _subtotal.value?.times(taxRate)
        _total.value = _subtotal.value!!.plus(_tax.value!!)
    }

    /**
     * Reset all values pertaining to the order.
     */
    fun resetOrder() {
        previousEntreePrice = 0.0
        previousSidePrice = 0.0
        previousAccompanimentPrice = 0.0
        _total.value = 0.0
        _tax.value = 0.0
        _subtotal.value = 0.0

        _entree.value = null
        _accompaniment.value = null
        _side.value = null

    }

}