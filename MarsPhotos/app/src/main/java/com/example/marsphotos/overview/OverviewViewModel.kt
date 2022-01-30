package com.example.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
        _status.value = "Set the Mars API status response here!"
    }

}