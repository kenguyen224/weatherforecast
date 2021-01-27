package com.example.kenv.weatherforecast.presentation.viewmodel

import androidx.lifecycle.*
import com.example.kenv.weatherforecast.domain.usecases.GetWeatherUseCase
import com.example.kenv.weatherforecast.presentation.uimodel.ListWeatherForecastUiModel
import com.example.kenv.weatherforecast.utils.Resource
import com.example.kenv.weatherforecast.utils.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherForecastLiveData = MutableLiveData<Resource<ListWeatherForecastUiModel>>()

    val weatherForecastLiveData: LiveData<Resource<ListWeatherForecastUiModel>> =
        _weatherForecastLiveData

    init {
        restoreSaveState()
        observeSaveState()
    }

    private fun restoreSaveState() = with(savedStateHandle) {
        get<ListWeatherForecastUiModel>(WEATHER_FORECAST_SAVE_STATE)?.let {
            _weatherForecastLiveData.postValue(Resource.Success(it))
        }
        get<Throwable>(WEATHER_FORECAST_ERROR_SAVE_STATE)?.let {
            _weatherForecastLiveData.postValue(Resource.Error(it))
        }
        get<Boolean>(WEATHER_FORECAST_PROCESSING_SAVE_STATE)?.let {
            _weatherForecastLiveData.postValue(Resource.Processing(it))
        }
    }

    private fun observeSaveState() {
        MediatorLiveData<Nothing>().apply {
            addSource(_weatherForecastLiveData) {
                when (it) {
                    is Resource.Success -> {
                        savedStateHandle.remove<Throwable>(WEATHER_FORECAST_ERROR_SAVE_STATE)
                        savedStateHandle.remove<Boolean>(WEATHER_FORECAST_PROCESSING_SAVE_STATE)
                        savedStateHandle.set(WEATHER_FORECAST_SAVE_STATE, it.data)
                    }
                    is Resource.Error -> {
                        savedStateHandle.remove<ListWeatherForecastUiModel>(
                            WEATHER_FORECAST_SAVE_STATE
                        )
                        savedStateHandle.remove<Boolean>(WEATHER_FORECAST_PROCESSING_SAVE_STATE)
                        savedStateHandle.set(WEATHER_FORECAST_ERROR_SAVE_STATE, it.exception)
                    }
                    is Resource.Processing -> {
                        savedStateHandle.remove<ListWeatherForecastUiModel>(
                            WEATHER_FORECAST_SAVE_STATE
                        )
                        savedStateHandle.remove<Throwable>(WEATHER_FORECAST_ERROR_SAVE_STATE)
                        savedStateHandle.set(WEATHER_FORECAST_PROCESSING_SAVE_STATE, it.loading)
                    }
                }
            }
        }.observeForever { }
    }

    fun getWeather(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherForecastLiveData.postValue(Resource.Processing(true))
            getWeatherUseCase(key)
                .catch { error ->
                    _weatherForecastLiveData.postValue(Resource.Error(error))
                }
                .collect {
                    _weatherForecastLiveData.postValue(Resource.Success(data = it.toUiModel()))
                }
        }
    }

    companion object {
        private const val WEATHER_FORECAST_SAVE_STATE = "weather_forecast_save_state"
        private const val WEATHER_FORECAST_ERROR_SAVE_STATE = "weather_forecast_error_save_state"
        private const val WEATHER_FORECAST_PROCESSING_SAVE_STATE =
            "weather_forecast_processing_save_state"
    }
}
