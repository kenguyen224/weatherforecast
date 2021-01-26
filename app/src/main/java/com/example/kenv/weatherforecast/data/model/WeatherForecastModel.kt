package com.example.kenv.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by KeNV on 23,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
data class WeatherForecastModel(
    @SerializedName("dt") val epochTime: Long,
    @SerializedName("temp") val temperatureModel: TemperatureModel,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("weather") val weatherDetails: List<WeatherDetailModel>
)
