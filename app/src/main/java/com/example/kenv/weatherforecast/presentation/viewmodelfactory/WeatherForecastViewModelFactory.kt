package com.example.kenv.weatherforecast.presentation.viewmodelfactory

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.kenv.weatherforecast.domain.usecases.GetWeatherUseCase
import com.example.kenv.weatherforecast.presentation.viewmodel.WeatherForecastViewModel
import javax.inject.Inject

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
class WeatherForecastViewModelFactory @Inject constructor(
    activity: Activity,
    private val getWeatherUseCase: GetWeatherUseCase
) : AbstractSavedStateViewModelFactory(activity as SavedStateRegistryOwner, Bundle.EMPTY) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        require(modelClass == WeatherForecastViewModel::class.java) {
            "Invalid viewModel class: ${modelClass.simpleName}"
        }
        return WeatherForecastViewModel(handle, getWeatherUseCase) as T
    }
}
