package com.example.kenv.weatherforecast.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by KeNV on 23,January,2021
 * VNG company,
 * HCM, Viet Nam
 */

data class WeatherDetailModel(
    @SerializedName("description") val description: String
)
