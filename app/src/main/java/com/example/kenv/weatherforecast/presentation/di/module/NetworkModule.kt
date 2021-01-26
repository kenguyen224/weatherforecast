package com.example.kenv.weatherforecast.presentation.di.module

import com.example.kenv.weatherforecast.data.service.WeatherForecastService
import com.example.kenv.weatherforecast.presentation.di.scope.FeatureScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Module
class NetworkModule {
    companion object URL {
        private const val WEATHER_FORECAST_URL = "https://api.openweathermap.org"
    }

    @Provides
    @FeatureScope
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WEATHER_FORECAST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @FeatureScope
    fun provideWeatherForecastService(retrofit: Retrofit): WeatherForecastService {
        return retrofit.create(WeatherForecastService::class.java)
    }
}
