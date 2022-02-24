package com.example.inventoryapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.inventoryapp.data.Item
import com.example.inventoryapp.data.ItemDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel(){

    val allItems: LiveData<List<Item>> = itemDao.getAllItems().asLiveData()

    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String):Boolean{
        if (itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()) {
            return false
        }
        return true
    }

    fun addNewItem(itemName: String, itemPrice: String, itemCount: String){
        val newItem = getNewItemEntry(itemName, itemPrice, itemCount)
        insertItem(newItem)
    }

    private fun insertItem(item:Item){
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewItemEntry(name:String,price:String,qty:String):Item{
        return Item(
            name = name,
            price = price.toDouble(),
            quantity = qty.toInt()
        )
    }

    fun getItem(id:Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }
}