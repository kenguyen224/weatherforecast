package com.example.kenv.weatherforecast.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Entity(
    tableName = "WeatherForecast"
)
data class WeatherForecastLocalEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id") var id: Int = 0,
    @ColumnInfo(name = "CityName") val cityName: String,
    @ColumnInfo(name = "EpochTime") val epochTime: Long,
    @Embedded val temperature: Temperature,
    @ColumnInfo(name = "Pressure") val pressure: Int,
    @ColumnInfo(name = "Humidity") val humidity: Int,
    @Embedded val weatherDetail: WeatherDetail
)
