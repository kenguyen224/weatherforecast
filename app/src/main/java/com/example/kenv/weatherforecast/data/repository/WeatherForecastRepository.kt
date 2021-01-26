package com.example.kenv.weatherforecast.data.repository

import android.util.Log
import com.example.kenv.weatherforecast.data.model.WeatherForecastResponse
import com.example.kenv.weatherforecast.data.service.WeatherForecastService
import com.example.kenv.weatherforecast.data.storage.IWeatherForecastStorage
import com.example.kenv.weatherforecast.data.storage.entity.CityWeatherForecast
import com.example.kenv.weatherforecast.utils.Constant
import com.example.kenv.weatherforecast.utils.Result
import com.example.kenv.weatherforecast.utils.toDatabaseEntity
import com.example.kenv.weatherforecast.utils.toWeatherForecastModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
class WeatherForecastRepository @Inject constructor(
    private val service: WeatherForecastService,
    private val storage: IWeatherForecastStorage
) :
    IWeatherForecastRepository {
    override suspend fun getWeather(
        cityName: String,
        numberForecast: Int,
        appId: String,
        unit: String
    ): Flow<Result<WeatherForecastResponse>> {
        return flow {
            try {
                val local = getLocalEntity(cityName)
                if (local == null || System.currentTimeMillis() >= local.city.expiryTime) {
                    emit(Result.Success(fetch(cityName, numberForecast, appId, unit)))
                } else {
                    Log.d("WeatherForecast", "Get weather from local $cityName")
                    emit(Result.Success(WeatherForecastResponse(local.weatherForecast.toWeatherForecastModel())))
                }
            } catch (throwable: Throwable) {
                val localData = storage.getWeather(cityName)
                val weatherForecast = localData?.weatherForecast
                if (!weatherForecast.isNullOrEmpty()) {
                    emit(Result.Success(WeatherForecastResponse(weatherForecast.toWeatherForecastModel())))
                } else {
                    emit(Result.Error(throwable))
                }
            }
        }
    }

    private suspend fun fetch(
        cityName: String,
        numberForecast: Int,
        appId: String,
        unit: String
    ): WeatherForecastResponse {
        Log.d("WeatherForecast", "Fetch weather $cityName")
        val response = service.getWeather(
            cityName,
            numberForecast,
            appId,
            unit
        )
        storage.save(
            cityName,
            System.currentTimeMillis() + Constant.EXPIRY_TIME_MILLISECOND,
            response.weatherForecastList.toDatabaseEntity(cityName)
        )
        return response
    }

    private suspend fun getLocalEntity(cityName: String): CityWeatherForecast? =
        storage.getWeather(cityName)
}
