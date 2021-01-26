package com.example.kenv.weatherforecast.domain.entity

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
data class TemperatureEntity(
    val min: Float,
    val max: Float
) {
    fun getAverageTemperature(): Float = (min + max) / 2
}
