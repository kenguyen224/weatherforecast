package com.example.kenv.weatherforecast.presentation.uimodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by KeNV on 26,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Parcelize
data class WeatherForecastUIModel(
    val dateTime: String,
    val temperature: String,
    val pressure: String,
    val humidity: String,
    val description: String
) : Parcelable
