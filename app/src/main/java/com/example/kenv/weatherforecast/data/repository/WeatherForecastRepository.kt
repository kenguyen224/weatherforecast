package com.example.kenv.weatherforecast.data.repository

import android.util.Log
import com.example.kenv.weatherforecast.data.model.WeatherForecastResponse
import com.example.kenv.weatherforecast.data.service.WeatherForecastService
import com.example.kenv.weatherforecast.data.storage.IWeatherForecastStorage
import com.example.kenv.weatherforecast.data.storage.entity.CityWeatherForecast
import com.example.kenv.weatherforecast.domain.repository.IWeatherForecastRepository
import com.example.kenv.weatherforecast.utils.Constant
import com.example.kenv.weatherforecast.utils.Result
import com.example.kenv.weatherforecast.utils.api.safeApiCalling
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
    ): Flow<Result<WeatherForecastResponse>> = flow {
        val local = getLocalEntity(cityName)
        if (local == null || System.currentTimeMillis() >= local.city.expiryTime) {
            when (val response =
                safeApiCalling { service.getWeather(cityName, numberForecast, appId, unit) }) {
                is Result.Success -> {
                    Log.d("WeatherForecast", "Fetch weather: $cityName")
                    cacheResponse(cityName, response.data)
                    emit(response)
                }
                is Result.Error -> {
                    val localEntity = local?.weatherForecast
                    if (!localEntity.isNullOrEmpty()) {
                        emit(Result.Success(WeatherForecastResponse(localEntity.toWeatherForecastModel())))
                    } else {
                        emit(response)
                    }
                }
            }
        } else {
            Log.d("WeatherForecast", "Get weather from local $cityName")
            emit(Result.Success(WeatherForecastResponse(local.weatherForecast.toWeatherForecastModel())))
        }
    }

    private suspend fun cacheResponse(cityName: String, response: WeatherForecastResponse) {
        storage.save(
            cityName,
            System.currentTimeMillis() + Constant.EXPIRY_TIME_MILLISECOND,
            response.weatherForecastList.toDatabaseEntity(cityName)
        )
    }

    private suspend fun getLocalEntity(cityName: String): CityWeatherForecast? =
        storage.getWeather(cityName)
}
