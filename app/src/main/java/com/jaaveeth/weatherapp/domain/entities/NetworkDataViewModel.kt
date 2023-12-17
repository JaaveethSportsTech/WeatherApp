package com.jaaveeth.weatherapp.domain.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaaveeth.weatherapp.domain.usecase.NetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkDataViewModel @Inject constructor(val useCase: NetworkUseCase) : ViewModel() {

    private var _weatherData = MutableLiveData<WeatherModel>()
    var weatherData: LiveData<WeatherModel> = _weatherData

    private var _foreCastData = MutableLiveData<ForecastModel>()
    var foreCastData: LiveData<ForecastModel> = _foreCastData

    var errorMessage = MutableLiveData("")
    var isLoading = MutableLiveData(false)

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {

            isLoading.postValue(true)

            useCase.getWeather().catch { error ->
                errorMessage.postValue(error.message ?: "")
                isLoading.postValue(false)
            }.collect {
                _weatherData.postValue(it.execute().body())
                isLoading.postValue(false)
            }
        }
    }

    fun getForecast() {
        viewModelScope.launch(Dispatchers.IO) {

            isLoading.postValue(true)

            useCase.getForecast().catch { error ->
                errorMessage.postValue(error.message ?: "")
                isLoading.postValue(false)
            }.collect {
                _foreCastData.postValue(it.execute().body())
                isLoading.postValue(false)
            }
        }
    }

}