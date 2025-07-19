package com.android.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherapp.domain.api.Constant
import com.android.weatherapp.domain.api.NetworkResponse
import com.android.weatherapp.domain.api.RetrofitInstance
import com.android.weatherapp.domain.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
     val weatherResult :LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city :  String) {
         Log.i("City Name : ",city)
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response =  weatherApi.getWeather(Constant.API_KEY,city)
                if(response.isSuccessful) {
                    Log.i("Response ",response.body().toString())
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    Log.i("Error ",response.message())
                    _weatherResult.value = NetworkResponse.Error("Failed to load data ")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Failed to load data ")

            }

        }
    }
}