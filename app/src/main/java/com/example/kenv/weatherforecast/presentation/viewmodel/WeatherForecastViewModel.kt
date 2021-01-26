package com.example.kenv.weatherforecast.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenv.weatherforecast.domain.usecases.GetWeatherUseCase
import com.example.kenv.weatherforecast.presentation.uimodel.WeatherForecastUIModel
import com.example.kenv.weatherforecast.utils.Resource
import com.example.kenv.weatherforecast.utils.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _weatherForecastLiveData = MutableLiveData<Resource<List<WeatherForecastUIModel>>>()
    val weatherForecastLiveData: LiveData<Resource<List<WeatherForecastUIModel>>> =
        _weatherForecastLiveData

    fun getWeather(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherForecastLiveData.postValue(Resource.Processing())
            getWeatherUseCase(key)
                .catch { error ->
                    _weatherForecastLiveData.postValue(Resource.Error(error))
                }
                .collect {
                    _weatherForecastLiveData.postValue(Resource.Success(it.toUiModel()))
                }
        }
    }
}
