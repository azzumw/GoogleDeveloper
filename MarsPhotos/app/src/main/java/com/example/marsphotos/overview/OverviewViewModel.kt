package com.example.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch

/**
 * This is responsible for making the network call
 * use LiveData with lifecycle-aware data binding to update
 * the app UI when the data changes.
 * */
class OverviewViewModel :ViewModel() {

    private val _status = MutableLiveData<String>("Hello")
    val status : LiveData<String> = _status

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos(){
        /**
         * A ViewModelScope is the built-in coroutine scope defined for each ViewModel in your app.
         * Any coroutine launched in this scope is automatically canceled if the ViewModel
         * is cleared*/
        viewModelScope.launch {
            val listResult = MarsApi.retrofitService.getPhotos()
            _status.value = listResult
        }
    }

}