package com.amonteiro.a2023_12_kampus

import android.content.Context
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {

    private var _data = MutableLiveData<WeatherBean>(null)
    var data :LiveData<WeatherBean> = _data


    private var _errorMessage = MutableLiveData<String>(null)
    var errorMessage :LiveData<String> = _errorMessage

    var _runInProgress = MutableLiveData(false)
    var runInProgress :LiveData<Boolean> = _runInProgress

    fun setError(errorMessage : String?){
        _errorMessage.postValue(errorMessage)

    }

    fun loadData(cityName :String) {
        if (_runInProgress.value != true) {
            _errorMessage.postValue( null)
            _data.postValue(null)
            _runInProgress.postValue(true)

            thread {
                try {
                    _data.postValue(WeatherAPI.loadWeather(cityName))
                }
                catch (e: Exception) {
                    _errorMessage.postValue("Une erreur est survenue : ${e.message}")
                }

                _runInProgress.postValue(false)
            }
        }
    }



}