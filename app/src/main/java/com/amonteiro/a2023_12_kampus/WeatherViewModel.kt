package com.amonteiro.a2023_12_kampus

import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    var data = MutableLiveData<WeatherBean>()
    var errorMessage = MutableLiveData<String>()

    fun loadData(cityName :String) {
        errorMessage.value = ""
        data.value = null;

        try {
            data.postValue(WeatherAPI.loadWeather(cityName))
        }
        catch (e: Exception) {
            errorMessage.postValue( "Une erreur est survenue : ${e.message}")
        }
    }

}