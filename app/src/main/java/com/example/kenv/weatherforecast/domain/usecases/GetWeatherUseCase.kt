package com.example.kenv.weatherforecast.domain.usecases

import com.example.kenv.weatherforecast.data.repository.IWeatherForecastRepository
import com.example.kenv.weatherforecast.domain.entity.WeatherForecastEntity
import com.example.kenv.weatherforecast.utils.Constant
import com.example.kenv.weatherforecast.utils.Result
import com.example.kenv.weatherforecast.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
class GetWeatherUseCase @Inject constructor(
    private val repository: IWeatherForecastRepository
) {
    suspend operator fun invoke(cityName: String): Flow<List<WeatherForecastEntity>> {
        return repository.getWeather(
            cityName,
            Constant.NUMBER_FORECAST,
            Constant.APP_ID,
            Constant.TEMP_UNIT
        ).map {
            when (it) {
                is Result.Success -> {
                    it.data.weatherForecastList.toEntity()
                }
                is Result.Error -> {
                    throw it.exception
                }
            }
        }
    }
}
