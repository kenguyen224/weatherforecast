package com.example.kenv.weatherforecast.data.service

import com.example.kenv.weatherforecast.data.model.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by KeNV on 23,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
interface WeatherForecastService {
    @GET("/data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("cnt") numberForecast: Int,
        @Query("appid") applicationId: String,
        @Query("units") unit: String
    ): WeatherForecastResponse
}
