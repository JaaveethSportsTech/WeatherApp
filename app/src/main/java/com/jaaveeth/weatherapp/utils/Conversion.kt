package com.jaaveeth.weatherapp.utils

import com.jaaveeth.weatherapp.domain.entities.WeatherData
import com.jaaveeth.weatherapp.domain.entities.WeatherInfo
import java.text.SimpleDateFormat
import java.util.*

object Conversion {

    @JvmStatic fun kelvinToCelsius(kelvin: Double): String {
        return String.format("%.1f", kelvin - 273.15).plus("°")
    }

    fun mapWeekdaysAndTemperatures(weatherDataList: List<WeatherData>): List<WeatherInfo> {
        val result = mutableListOf<WeatherInfo>()

        var currentDate = ""

        for (weatherData in weatherDataList) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.format(Date(weatherData.dt * 1000))

            if (date != currentDate) {
                currentDate = date
                val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(Date(weatherData.dt * 1000))
                val weatherInfo = WeatherInfo(date, dayOfWeek, weatherData.temp)
                result.add(weatherInfo)
            }
        }

        return result
    }
}