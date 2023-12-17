package com.jaaveeth.weatherapp.domain.repo

import com.jaaveeth.weatherapp.domain.entities.ForecastModel
import com.jaaveeth.weatherapp.domain.entities.WeatherModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call


interface NetworkDataRepository {
    suspend fun getWeather(): Flow<Call<WeatherModel>>
    suspend fun getForecast(): Flow<Call<ForecastModel>>
}