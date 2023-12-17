package com.jaaveeth.weatherapp.domain.entities

data class ForecastModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForeCastList>,
    val message: Int
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Long,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

data class ForeCastList(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)