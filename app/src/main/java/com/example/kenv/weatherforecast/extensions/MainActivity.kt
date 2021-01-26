package com.example.kenv.weatherforecast.extensions

import com.example.kenv.weatherforecast.AndroidApplication.Companion.getAppComponent
import com.example.kenv.weatherforecast.presentation.activity.MainActivity
import com.example.kenv.weatherforecast.presentation.di.component.DaggerWeatherForecastComponent

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
fun MainActivity.createComponent() {
    DaggerWeatherForecastComponent.factory().create(this, getAppComponent()).also {
        MainActivity.component = it
    }
}
