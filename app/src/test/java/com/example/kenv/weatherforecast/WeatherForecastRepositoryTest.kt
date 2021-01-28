package com.example.kenv.weatherforecast

import com.example.kenv.weatherforecast.data.model.WeatherForecastResponse
import com.example.kenv.weatherforecast.data.repository.WeatherForecastRepository
import com.example.kenv.weatherforecast.data.service.WeatherForecastService
import com.example.kenv.weatherforecast.data.storage.IWeatherForecastStorage
import com.example.kenv.weatherforecast.data.storage.entity.CityEntity
import com.example.kenv.weatherforecast.data.storage.entity.CityWeatherForecast
import com.example.kenv.weatherforecast.data.storage.entity.Temperature
import com.example.kenv.weatherforecast.data.storage.entity.WeatherDetail
import com.example.kenv.weatherforecast.data.storage.entity.WeatherForecast
import com.example.kenv.weatherforecast.domain.repository.IWeatherForecastRepository
import com.example.kenv.weatherforecast.utils.Constant
import com.example.kenv.weatherforecast.utils.Result
import com.example.kenv.weatherforecast.utils.exceptions.BodyResponseException
import com.example.kenv.weatherforecast.utils.exceptions.ErrorCode
import com.example.kenv.weatherforecast.utils.toWeatherForecastModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.reset
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response


/**
 * Created by KeNV on 28,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherForecastRepositoryTest {
    private lateinit var repositoryTest: IWeatherForecastRepository

    @Mock
    private lateinit var mockStorage: IWeatherForecastStorage

    @Mock
    private lateinit var mockService: WeatherForecastService

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
        reset(mockService)
        reset(mockStorage)
        repositoryTest = WeatherForecastRepository(mockService, mockStorage)
    }

    @Test
    fun `get Weather from api Test`() = runBlocking {
        `when`(mockStorage.getWeather(mockCityName)).thenAnswer {
            null
        }
        `when`(
            mockService.getWeather(
                mockCityName,
                Constant.NUMBER_FORECAST,
                Constant.APP_ID,
                Constant.TEMP_UNIT
            )
        ).thenAnswer {
            WeatherForecastResponse(mockWeatherForecastList.toWeatherForecastModel())
        }
        var actual: WeatherForecastResponse? = null
        repositoryTest.getWeather(
            mockCityName,
            Constant.NUMBER_FORECAST,
            Constant.APP_ID,
            Constant.TEMP_UNIT
        ).collect {
            actual = (it as? Result.Success)?.data
        }
        val expect = WeatherForecastResponse(mockWeatherForecastList.toWeatherForecastModel())
        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `get weather from cache test`() = runBlocking {
        `when`(mockStorage.getWeather(mockCityName)).thenAnswer {
            CityWeatherForecast(
                CityEntity(
                    mockCityName,
                    System.currentTimeMillis() + Constant.EXPIRY_TIME_MILLISECOND
                ),
                mockWeatherForecastList
            )
        }
        var actual: WeatherForecastResponse? = null
        repositoryTest.getWeather(
            mockCityName,
            Constant.NUMBER_FORECAST,
            Constant.APP_ID,
            Constant.TEMP_UNIT
        ).collect {
            actual = (it as? Result.Success)?.data
        }
        val expect = WeatherForecastResponse(mockWeatherForecastList.toWeatherForecastModel())
        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `get Weather from api not found Test`() = runBlocking {
        `when`(mockStorage.getWeather(mockCityName)).thenAnswer {
            null
        }
        val httpException = HttpException(
            Response.error<WeatherForecastResponse>(
                ErrorCode.CITY_NOT_FOUND,
                mock(ResponseBody::class.java)
            )
        )
        `when`(
            mockService.getWeather(
                mockCityName,
                Constant.NUMBER_FORECAST,
                Constant.APP_ID,
                Constant.TEMP_UNIT
            )
        ).thenThrow(httpException)
        var actual: Throwable? = null
        repositoryTest.getWeather(
            mockCityName,
            Constant.NUMBER_FORECAST,
            Constant.APP_ID,
            Constant.TEMP_UNIT
        ).collect {
            actual = (it as? Result.Error)?.exception
        }
        val expect =
            BodyResponseException("City not found, please try with another", httpException)
        Assert.assertEquals(expect.message, actual?.message)
    }
}
