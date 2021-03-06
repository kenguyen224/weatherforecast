package com.example.kenv.weatherforecast.data.storage

import com.example.kenv.weatherforecast.data.storage.entity.CityWeatherForecast
import com.example.kenv.weatherforecast.data.storage.entity.WeatherForecast

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
interface IWeatherForecastStorage {
    suspend fun save(
        cityName: String,
        expiryTime: Long,
        weatherForecast: List<WeatherForecast>
    )

    suspend fun getWeather(cityName: String): CityWeatherForecast?
}
