package com.example.forageapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.forageapp.data.ForageableDao
import com.example.forageapp.model.Forageable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForageableViewModel(val dao:ForageableDao):ViewModel() {

    val forageables : LiveData<List<Forageable>> = dao.getForageables().asLiveData()


    fun getAForageable(id: Long): LiveData<Forageable> {

        return dao.getForageable(id).asLiveData()
    }

    fun addForageable(name: String, address: String, inSeason: Boolean, notes: String) {
        val forageable = Forageable(
            name = name,
            address = address,
            inSeason = inSeason,
            notes = notes
        )
        insertForageable(forageable)
    }

    private fun insertForageable(forageable: Forageable){
        viewModelScope.launch {
            dao.insert(forageable)
        }
    }

    fun updateForageable(
        id: Long,
        name: String,
        address: String,
        inSeason: Boolean,
        notes: String
    ) {
        val forageable = Forageable(
            id = id,
            name = name,
            address = address,
            inSeason = inSeason,
            notes = notes
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(forageable)
        }
    }

    fun deleteForageable(forageable: Forageable) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(forageable)
        }
    }

    fun isValidEntry(name: String, address: String): Boolean {
        return name.isNotBlank() && address.isNotBlank()
    }
}

