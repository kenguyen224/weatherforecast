package com.example.kenv.weatherforecast.data.storage

import com.example.kenv.weatherforecast.data.storage.entity.CityEntity
import com.example.kenv.weatherforecast.data.storage.entity.CityWeatherForecast
import com.example.kenv.weatherforecast.data.storage.entity.WeatherForecastLocalEntity
import javax.inject.Inject

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
class WeatherForecastStorage @Inject constructor(
    private val database: AppDatabase
) : IWeatherForecastStorage {
    override suspend fun save(
        cityName: String,
        expiryTime: Long,
        weatherForecast: List<WeatherForecastLocalEntity>,
    ) =
        with(database.weatherForecastDao()) {
            insertCityName(CityEntity(cityName, expiryTime))
            deleteWeatherForecast(cityName)
            insertWeatherForecast(weatherForecast)
        }

    override suspend fun getWeather(cityName: String): CityWeatherForecast? =
        database.weatherForecastDao().getWeather(cityName)
}
