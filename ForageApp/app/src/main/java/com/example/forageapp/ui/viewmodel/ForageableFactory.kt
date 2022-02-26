package com.example.forageapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forageapp.data.ForageableDao
import java.lang.IllegalArgumentException

class ForageableFactory(private val dao:ForageableDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ForageableViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ForageableViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}