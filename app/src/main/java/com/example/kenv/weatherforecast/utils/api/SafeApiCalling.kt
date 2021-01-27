package com.example.kenv.weatherforecast.utils.api

import com.example.kenv.weatherforecast.utils.ErrorMessageProvider
import com.example.kenv.weatherforecast.utils.Result
import com.example.kenv.weatherforecast.utils.exceptions.BodyResponseException
import com.example.kenv.weatherforecast.utils.exceptions.GenericException
import com.example.kenv.weatherforecast.utils.exceptions.NetworkException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by KeNV on 27,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
suspend fun <T> safeApiCalling(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        return when (throwable) {
            is HttpException -> Result.Error(
                BodyResponseException(
                    ErrorMessageProvider.getErrorMessage(throwable.code()),
                    throwable
                )
            )
            is SocketTimeoutException, is UnknownHostException -> Result.Error(
                NetworkException(
                    ErrorMessageProvider.NETWORK_ERROR_MESSAGE,
                    throwable
                )
            )
            else -> Result.Error(
                GenericException(
                    ErrorMessageProvider.GENERIC_ERROR_MESSAGE,
                    throwable
                )
            )
        }
    }
}
