package com.example.kenv.weatherforecast

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kenv.weatherforecast.data.storage.AppDatabase
import com.example.kenv.weatherforecast.data.storage.IWeatherForecastStorage
import com.example.kenv.weatherforecast.data.storage.WeatherForecastDao
import com.example.kenv.weatherforecast.data.storage.WeatherForecastStorage
import com.example.kenv.weatherforecast.data.storage.entity.CityEntity
import com.example.kenv.weatherforecast.data.storage.entity.CityWeatherForecast
import com.example.kenv.weatherforecast.data.storage.entity.Temperature
import com.example.kenv.weatherforecast.data.storage.entity.WeatherDetail
import com.example.kenv.weatherforecast.data.storage.entity.WeatherForecast
import com.example.kenv.weatherforecast.utils.Constant
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by KeNV on 28,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@RunWith(AndroidJUnit4::class)
class WeatherForecastStorageTest {
    private lateinit var weatherForecastDao: WeatherForecastDao
    private lateinit var db: AppDatabase
    private lateinit var storage: IWeatherForecastStorage

    private val mockCityName = "Ho Chi Minh"
    private val mockWeatherForecastList = listOf(
        WeatherForecast(
            1,
            mockCityName,
            123465676L,
            Temperature(20f, 30f),
            1000,
            50,
            WeatherDetail("light sun")
        )
    )

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        weatherForecastDao = db.weatherForecastDao()
        storage = WeatherForecastStorage(db)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveAndGetDataTest() = runBlocking {
        storage.save(mockCityName, Constant.EXPIRY_TIME_MILLISECOND, mockWeatherForecastList)
        val cache = storage.getWeather(mockCityName)
        val expect =
            CityWeatherForecast(
                CityEntity(mockCityName, Constant.EXPIRY_TIME_MILLISECOND),
                mockWeatherForecastList
            )
        assertThat(cache, equalTo(expect))
    }
}
