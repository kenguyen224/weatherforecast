package com.example.kenv.weatherforecast.domain.entity

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
data class WeatherForecastEntity(
    val epochTime: Long,
    val temperatureEntity: TemperatureEntity,
    val pressure: Int,
    val humidity: Int,
    val weatherDescriptions: List<WeatherDescriptionEntity>
)
