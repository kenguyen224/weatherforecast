package com.example.kenv.weatherforecast.data.storage

import androidx.room.*
import com.example.kenv.weatherforecast.data.storage.entity.CityEntity
import com.example.kenv.weatherforecast.data.storage.entity.CityWeatherForecast
import com.example.kenv.weatherforecast.data.storage.entity.WeatherForecastLocalEntity

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Dao
interface WeatherForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityName(city: CityEntity)

    @Insert
    suspend fun insertWeatherForecast(weatherForecast: List<WeatherForecastLocalEntity>)

    @Query("SELECT * FROM WeatherForecast WHERE CityName LIKE :cityName")
    suspend fun getWeatherForecast(cityName: String): List<WeatherForecastLocalEntity>

    @Query("DELETE  FROM WeatherForecast WHERE CityName LIKE :cityName")
    suspend fun deleteWeatherForecast(cityName: String)

    @Transaction
    @Query("SELECT * FROM City WHERE CityName LIKE :cityName")
    suspend fun getWeather(cityName: String): CityWeatherForecast?
}
