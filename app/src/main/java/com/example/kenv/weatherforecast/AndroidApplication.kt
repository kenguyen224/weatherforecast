package com.example.kenv.weatherforecast

import android.app.Application
import com.example.kenv.weatherforecast.presentation.di.component.AppComponent

/**
 * Created by KeNV on 22,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
class AndroidApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = AppComponent.create(this)
        appComponent.inject(this)
    }

    companion object {
        private lateinit var instance: AndroidApplication
        fun getAppComponent() = instance.appComponent
    }
}
