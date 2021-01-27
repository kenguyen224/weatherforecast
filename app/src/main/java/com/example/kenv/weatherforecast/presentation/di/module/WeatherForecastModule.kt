package com.example.kenv.weatherforecast.presentation.di.module

import com.example.kenv.weatherforecast.data.repository.WeatherForecastRepository
import com.example.kenv.weatherforecast.data.storage.IWeatherForecastStorage
import com.example.kenv.weatherforecast.data.storage.WeatherForecastStorage
import com.example.kenv.weatherforecast.domain.repository.IWeatherForecastRepository
import com.example.kenv.weatherforecast.presentation.di.scope.FeatureScope
import dagger.Binds
import dagger.Module

/**
 * Created by KeNV on 22,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Module
abstract class WeatherForecastModule {
    @Binds
    @FeatureScope
    abstract fun bindWeatherForecastRepository(weatherForecastRepository: WeatherForecastRepository): IWeatherForecastRepository

    @Binds
    @FeatureScope
    abstract fun bindWeatherForecastStorage(weatherForecastStorage: WeatherForecastStorage): IWeatherForecastStorage
}
