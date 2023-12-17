package com.jaaveeth.weatherapp.domain.usecase

import com.jaaveeth.weatherapp.domain.entities.ForecastModel
import com.jaaveeth.weatherapp.domain.entities.WeatherModel
import com.jaaveeth.weatherapp.domain.repo.NetworkDataRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call


class NetworkUseCase(var repo: NetworkDataRepository) {
    suspend fun getWeather(): Flow<Call<WeatherModel>> = repo.getWeather()
    suspend fun getForecast(): Flow<Call<ForecastModel>> = repo.getForecast()
}