package com.android.weatherapp.domain.api

data class WeatherModel(
    val current: Current,
    val location: Location
)