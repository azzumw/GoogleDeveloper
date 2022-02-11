package com.example.amphibian.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibian.network.Amphibian
import com.example.amphibian.network.AmphibianApi
import kotlinx.coroutines.launch

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel:ViewModel() {

    private val _amphibians = MutableLiveData<List<Amphibian>>()
    val amphibians : LiveData<List<Amphibian>> = _amphibians

    private val _singleAmphibian = MutableLiveData<Amphibian>()
    val singleAmphibian : LiveData<Amphibian> = _singleAmphibian

    private val _status = MutableLiveData<AmphibianApiStatus>()
    val status : LiveData<AmphibianApiStatus> = _status

    init {

        getAmphibians()
    }

    private fun getAmphibians(){

        viewModelScope.launch {

            try {

                val result = AmphibianApi.retrofitService.getAmphibians()
                _amphibians.value = result
                    _status.value = AmphibianApiStatus.DONE

            }catch (e: Exception){
                _status.value = AmphibianApiStatus.ERROR
                _amphibians.value = listOf()
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        _singleAmphibian.value = amphibian
    }
}