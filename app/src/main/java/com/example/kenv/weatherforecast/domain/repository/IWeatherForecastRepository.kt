package com.example.kenv.weatherforecast.domain.repository

import com.example.kenv.weatherforecast.data.model.WeatherForecastResponse
import com.example.kenv.weatherforecast.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
interface IWeatherForecastRepository {
    suspend fun getWeather(
        cityName: String,
        numberForecast: Int,
        appId: String,
        unit: String
    ): Flow<Result<WeatherForecastResponse>>
}
