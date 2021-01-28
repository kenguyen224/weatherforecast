package com.example.kenv.weatherforecast.presentation.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
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
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WEATHER_FORECAST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}
