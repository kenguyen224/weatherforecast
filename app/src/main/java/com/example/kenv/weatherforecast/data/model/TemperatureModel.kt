package com.example.kenv.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by KeNV on 23,January,2021
 * VNG company,
 * HCM, Viet Nam
 */

data class TemperatureModel(
    @SerializedName("min") val min: Float,
    @SerializedName("max") val max: Float
)
