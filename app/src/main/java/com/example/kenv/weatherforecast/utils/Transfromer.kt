package com.example.kenv.weatherforecast.utils

import com.example.kenv.weatherforecast.data.model.TemperatureModel
import com.example.kenv.weatherforecast.data.model.WeatherDetailModel
import com.example.kenv.weatherforecast.data.model.WeatherForecastModel
import com.example.kenv.weatherforecast.data.storage.entity.Temperature
import com.example.kenv.weatherforecast.data.storage.entity.WeatherDetail
import com.example.kenv.weatherforecast.data.storage.entity.WeatherForecast
import com.example.kenv.weatherforecast.domain.entity.TemperatureEntity
import com.example.kenv.weatherforecast.domain.entity.WeatherDescriptionEntity
import com.example.kenv.weatherforecast.domain.entity.WeatherForecastEntity
import com.example.kenv.weatherforecast.presentation.uimodel.WeatherForecastUIModel

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
fun List<WeatherForecastModel>.toEntity() = map {
    it.toEntity()
}

fun WeatherForecastModel.toEntity() = WeatherForecastEntity(
    epochTime = this.epochTime,
    temperatureEntity = temperatureModel.toEntity(),
    pressure = pressure,
    humidity = humidity,
    weatherDescriptions = weatherDetails.map { it.toEntity() }
)

fun WeatherDetailModel.toEntity() = WeatherDescriptionEntity(description)

fun TemperatureModel.toEntity() = TemperatureEntity(min, max)

fun List<WeatherForecastModel>.toDatabaseEntity(cityName: String): List<WeatherForecast> =
    map {
        WeatherForecast(
            cityName = cityName,
            epochTime = it.epochTime,
            temperature = it.temperatureModel.toDatabaseEntity(),
            pressure = it.pressure,
            humidity = it.humidity,
            weatherDetail = it.weatherDetails.first().toDatabaseEntity()
        )
    }

fun TemperatureModel.toDatabaseEntity() = Temperature(minTemperature = min, maxTemperature = max)

fun List<WeatherForecast>.toWeatherForecastModel() = map {
    WeatherForecastModel(
        epochTime = it.epochTime,
        temperatureModel = it.temperature.toModel(),
        pressure = it.pressure,
        humidity = it.humidity,
        weatherDetails = listOf(it.weatherDetail.toModel())
    )
}

fun WeatherDetailModel.toDatabaseEntity() = WeatherDetail(description = description)

fun WeatherDetail.toModel() = WeatherDetailModel(description = description)

fun Temperature.toModel() = TemperatureModel(min = minTemperature, max = maxTemperature)

fun List<WeatherForecastEntity>.toUiModel(): List<WeatherForecastUIModel> = map {
    WeatherForecastUIModel(
        dateTime = formatDateString(it.epochTime),
        temperature = formatTemperatureCelsius(it.temperatureEntity.getAverageTemperature()),
        pressure = it.pressure.toString(),
        humidity = formatPercentage(it.humidity),
        description = it.weatherDescriptions.first().description
    )
}
