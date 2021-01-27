package com.example.kenv.weatherforecast.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kenv.weatherforecast.data.storage.entity.CityEntity
import com.example.kenv.weatherforecast.data.storage.entity.WeatherForecast

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Database(entities = [WeatherForecast::class, CityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherForecastDao(): WeatherForecastDao
}
