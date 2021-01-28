package com.example.kenv.weatherforecast.presentation.di.component

import android.app.Activity
import com.example.kenv.weatherforecast.presentation.activity.MainActivity
import com.example.kenv.weatherforecast.presentation.di.module.WeatherForecastModule
import com.example.kenv.weatherforecast.presentation.di.scope.FeatureScope
import com.example.kenv.weatherforecast.presentation.fragment.WeatherForecastFragment
import dagger.BindsInstance
import dagger.Component

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Component(
    dependencies = [AppComponent::class],
    modules = [WeatherForecastModule::class]
)
@FeatureScope
interface WeatherForecastComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(weatherForecastFragment: WeatherForecastFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: Activity,
            appComponent: AppComponent
        ): WeatherForecastComponent
    }
}
