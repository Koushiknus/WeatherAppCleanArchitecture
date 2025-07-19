package com.android.weatherapp.domain.api

//T refers to model
sealed class NetworkResponse<out T> {
    data class Success<out T>(val data : T) : NetworkResponse<T>()
    data class Error(val meesage : String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}