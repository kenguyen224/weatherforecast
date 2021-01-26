package com.example.kenv.weatherforecast.utils

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    class Error(val exception: Throwable) : Resource<Nothing>()
    class Processing(val data: Any? = null) : Resource<Nothing>()
}
