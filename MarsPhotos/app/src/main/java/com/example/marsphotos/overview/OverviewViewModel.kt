package com.example.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.MarsApi
import com.example.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MarsApiStatus { LOADING, ERROR, DONE }
/**
 * This is responsible for making the network call
 * use LiveData with lifecycle-aware data binding to update
 * the app UI when the data changes.
 * */
class OverviewViewModel :ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status : LiveData<MarsApiStatus> = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    val photos : LiveData<List<MarsPhoto>> = _photos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos(){
        /**
         * A ViewModelScope is the built-in coroutine scope defined for each ViewModel in your app.
         * Any coroutine launched in this scope is automatically canceled if the ViewModel
         * is cleared*/
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                _photos.value = listResult
                _status.value = MarsApiStatus.DONE
            }catch (e:Exception){
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }

}