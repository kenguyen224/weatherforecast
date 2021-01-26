package com.example.kenv.weatherforecast.data.storage.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
data class CityWeatherForecast(
    @Embedded val city: CityEntity,
    @Relation(
        parentColumn = "CityName",
        entityColumn = "CityName"
    )
    val weatherForecast: List<WeatherForecastLocalEntity>
)
