package com.jaaveeth.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jaaveeth.weatherapp.R
import com.jaaveeth.weatherapp.databinding.ActivityMainBinding
import com.jaaveeth.weatherapp.domain.entities.NetworkDataViewModel
import com.jaaveeth.weatherapp.domain.entities.WeatherData
import com.jaaveeth.weatherapp.domain.entities.WeatherInfo
import com.jaaveeth.weatherapp.utils.Conversion.kelvinToCelsius
import com.jaaveeth.weatherapp.utils.Conversion.mapWeekdaysAndTemperatures
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private val viewModel : NetworkDataViewModel by viewModels()
    private var weatherData : MutableList<WeatherInfo> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        fetchData()
        observeData()
    }

    private fun fetchData() {
        viewModel.getWeather()
        viewModel.getForecast()
    }

    private fun observeData() {
        viewModel.weatherData.observe(this) {
            binding.tvTemperature.text = kelvinToCelsius(it.main.temp)
        }

        viewModel.foreCastData.observe(this) {
            val data  = mapWeekdaysAndTemperatures(it.list.map {
                WeatherData(
                    dt = it.dt.toLong(),
                    temp = it.main.temp,
                    dtTxt = it.dt_txt
                )
            })
            weatherData.clear()
            weatherData.addAll(data)
            weatherData.removeAt(0)
            setPostAdapter()
        }

        viewModel.isLoading.observe(this) {_isLoading ->
            if (_isLoading) binding.progressbar.visibility = View.VISIBLE
            else binding.progressbar.visibility = View.GONE
        }
    }

    private fun setPostAdapter() {
        val adapter = WeatherListAdapter(weatherData)
        binding.rvForecastData.adapter = adapter
        simulateLoadingAndShowRecyclerView()
    }

    private fun simulateLoadingAndShowRecyclerView() {
        // After loading is complete, show the RecyclerView with an animation
        binding.rvForecastData.apply {
                // Set up and start the animation to slide in from the bottom
                val slideUpAnimation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.slide_up)
                startAnimation(slideUpAnimation)
                visibility = RecyclerView.VISIBLE
        }

    }
}