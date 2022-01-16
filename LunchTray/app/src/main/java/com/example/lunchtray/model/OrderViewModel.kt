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

    private val _subtotal = MutableLiveData(0.0)
    val subtotal : LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _total = MutableLiveData(0.0)
    val total : LiveData<String> = Transformations.map(_total){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _tax = MutableLiveData(0.0)
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

        // TODO: set the current entree value to the menu item corresponding to the passed in string
        _entree.value = menuItems[mEntree]

        // TODO: update the subtotal to reflect the price of the selected entree.
        updateSubtotal(entree.value!!.price)

    }

    /**
     * Set the side for the order.
     */
    fun setSide(side: String) {
        // TODO: if _side.value is not null, set the previous side price to the current side price.
        if (_side.value != null) {
             previousSidePrice = _side.value!!.price
        }

        // TODO: if _subtotal.value is not null subtract the previous side price from the current
        if(_subtotal.value != null){
            _subtotal.value = _subtotal.value?.minus(previousSidePrice)
        }
        //  subtotal value. This ensures that we only charge for the currently selected side.

        // TODO: set the current side value to the menu item corresponding to the passed in string
        _side.value = menuItems[side]
        // TODO: update the subtotal to reflect the price of the selected side.
        updateSubtotal(_side.value!!.price)
    }

    /**
     * Set the accompaniment for the order.
     */
    fun setAccompaniment(accompaniment: String) {
        // TODO: if _accompaniment.value is not null, set the previous accompaniment price to the
        //  current accompaniment price.
        if(_accompaniment.value != null){
            previousAccompanimentPrice = _accompaniment.value!!.price
        }
        // TODO: if _accompaniment.value is not null subtract the previous accompaniment price from
        //  the current subtotal value. This ensures that we only charge for the currently selected
        //  accompaniment.
        if(_subtotal.value != null){
            _subtotal.value = _subtotal.value?.minus(previousSidePrice)
        }

        // TODO: set the current accompaniment value to the menu item corresponding to the passed in
        //  string
        _accompaniment.value = menuItems[accompaniment]
        // TODO: update the subtotal to reflect the price of the selected accompaniment.
        updateSubtotal(_accompaniment.value!!.price)
    }


    /**
     * Update subtotal value.
     */
    private fun updateSubtotal(itemPrice: Double) {
        // TODO: if _subtotal.value is not null, update it to reflect the price of the recently
        //  added item.
        //  Otherwise, set _subtotal.value to equal the price of the item.
        if(_subtotal.value!=null){
            _subtotal.value = _subtotal.value!!.plus(itemPrice)
        }else{
            _subtotal.value = itemPrice
        }
        // TODO: calculate the tax and resulting total
        calculateTaxAndTotal()
    }

    /**
     * Calculate tax and update total.
     */
    fun calculateTaxAndTotal() {
        // TODO: set _tax.value based on the subtotal and the tax rate.
        _tax.value = _subtotal.value?.times(taxRate)
        // TODO: set the total based on the subtotal and _tax.value.
        _total.value = _subtotal.value!!.plus(_tax.value!!)
    }

    /**
     * Reset all values pertaining to the order.
     */
    fun resetOrder() {
        // TODO: Reset all values associated with an order
    }

}