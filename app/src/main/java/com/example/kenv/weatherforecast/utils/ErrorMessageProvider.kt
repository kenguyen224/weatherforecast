package com.example.kenv.weatherforecast.utils

import com.example.kenv.weatherforecast.utils.exceptions.ErrorCode

/**
 * Created by KeNV on 27,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
object ErrorMessageProvider {
    const val NETWORK_ERROR_MESSAGE =
        "Network is not available, please check your network connection and try again"
    const val GENERIC_ERROR_MESSAGE = "Something went wrong, please try again"

    fun getErrorMessage(errorCode: Int): String = when (errorCode) {
        ErrorCode.CITY_NOT_FOUND -> "City not found, please try with another"
        else -> "Some thing went wrong, please try again"
    }
}
