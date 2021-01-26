package com.example.kenv.weatherforecast.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Entity(
    primaryKeys = ["CityName"],
    tableName = "City"
)
data class CityEntity(
    @ColumnInfo(name = "CityName") val cityName: String,
    @ColumnInfo(name = "expiryTime") val expiryTime: Long
)
