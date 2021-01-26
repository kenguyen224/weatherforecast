package com.example.kenv.weatherforecast.presentation.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.kenv.weatherforecast.data.storage.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "weather_forecast_database"
        ).build()
    }
}
