package com.example.amphibian.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibian.network.AmphibianApi
import kotlinx.coroutines.launch

class AmphibianViewModel:ViewModel() {

    private val _amphibians = MutableLiveData<List<String>>()
    val amphibians : LiveData<List<String>>
    get() = _amphibians

    private val _status = MutableLiveData<String>()
    val status : LiveData<String> = _status

    init {

        getAmphibians()
    }

    private fun getAmphibians(){
        val list = listOf("Frog","Croc","Snake","Anaconda","Python")
        _amphibians.value = list
        viewModelScope.launch {

            try {
                val listResult = AmphibianApi.retrofitService.getAmphibians()

                _status.value = listResult.size.toString()
                Log.e("VIEWMODEL",status.value.toString())
            }catch (e: Exception){
                _status.value = "Failure: ${e.message}"
            }

        }
    }

}