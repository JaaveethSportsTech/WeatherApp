package com.jaaveeth.weatherapp.data.repoimpl

import com.jaaveeth.weatherapp.data.network.RetrofitAPI
import com.jaaveeth.weatherapp.domain.entities.ForecastModel
import com.jaaveeth.weatherapp.domain.entities.WeatherModel
import com.jaaveeth.weatherapp.domain.repo.NetworkDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import javax.inject.Inject

class NetworkDataRepositoryImpl @Inject constructor(private val api: RetrofitAPI) : NetworkDataRepository {

    private val APPID = "9b8cb8c7f11c077f8c4e217974d9ee40"
    private val CITY = "Bengaluru"

    override suspend fun getWeather(): Flow<Call<WeatherModel>> {
        return flow {
            emit(api.getWeather(CITY, APPID))
        }
    }

    override suspend fun getForecast(): Flow<Call<ForecastModel>> {
        return flow {
            emit(api.getForecast(CITY, APPID))
        }
    }

}