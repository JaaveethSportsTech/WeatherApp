package com.jaaveeth.weatherapp.data.network

import com.jaaveeth.weatherapp.domain.entities.ForecastModel
import com.jaaveeth.weatherapp.domain.entities.WeatherModel
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitAPI {

    // with query param , it will be added like : /weather?q=value1&APPID=value2
    @GET("weather")
    fun getWeather(@Query("q") q: String, @Query("APPID") appId: String): Call<WeatherModel>

    // with query param , it will be added like : /weather?q=value1&APPID=value2
    @GET("forecast")
    fun getForecast(@Query("q") q: String, @Query("APPID") appId: String): Call<ForecastModel>

}