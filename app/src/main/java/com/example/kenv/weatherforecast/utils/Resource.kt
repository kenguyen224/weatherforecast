package com.example.kenv.weatherforecast.utils

import android.os.Parcelable

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
sealed class Resource<out T : Parcelable> {
    class Success<out T : Parcelable>(val data: T) : Resource<T>()
    class Error(val exception: Throwable) : Resource<Nothing>()
    class Processing(val loading: Boolean = true) : Resource<Nothing>()
}
