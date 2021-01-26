package com.example.kenv.weatherforecast.utils

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
sealed class Result<out T> {
    class Success<out T>(val data: T) : Result<T>()
    class Error(val exception: Throwable) : Result<Nothing>()
}
