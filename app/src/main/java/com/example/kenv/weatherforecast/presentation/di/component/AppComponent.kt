package com.example.kenv.weatherforecast.presentation.di.component

import android.app.Application
import android.content.Context
import com.example.kenv.weatherforecast.AndroidApplication
import com.example.kenv.weatherforecast.data.storage.AppDatabase
import com.example.kenv.weatherforecast.presentation.di.module.AppModule
import com.example.kenv.weatherforecast.presentation.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import retrofit2.Retrofit

/**
 * Created by KeNV on 22,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Component(
    modules = [AppModule::class, NetworkModule::class]
)
@Singleton
interface AppComponent {

    fun inject(androidApplication: AndroidApplication)

    val context: Context
    val appDatabase: AppDatabase
    val retrofit: Retrofit

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    companion object {
        fun create(application: Application): AppComponent {
            return DaggerAppComponent.builder()
                .application(application)
                .build()
        }
    }
}
