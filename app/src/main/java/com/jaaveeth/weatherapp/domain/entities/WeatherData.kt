package com.jaaveeth.weatherapp.domain.entities

data class WeatherData(
    val dt: Long,
    val temp: Double,
    val dtTxt: String
)

data class WeatherInfo(
    val date: String,
    val dayOfWeek: String,
    val temperature: Double
)