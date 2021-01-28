package com.example.kenv.weatherforecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.example.kenv.weatherforecast.domain.entity.TemperatureEntity
import com.example.kenv.weatherforecast.domain.entity.WeatherDescriptionEntity
import com.example.kenv.weatherforecast.domain.entity.WeatherForecastEntity
import com.example.kenv.weatherforecast.domain.usecases.GetWeatherUseCase
import com.example.kenv.weatherforecast.presentation.uimodel.ListWeatherForecastUiModel
import com.example.kenv.weatherforecast.presentation.viewmodel.WeatherForecastViewModel
import com.example.kenv.weatherforecast.utils.Resource
import com.example.kenv.weatherforecast.utils.toUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.refEq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.reset
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by KeNV on 28,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherForecastViewModelTest {

    @Mock
    private lateinit var getWeatherUseCaseMock: GetWeatherUseCase

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var weatherForecastObserver: Observer<Resource<ListWeatherForecastUiModel>>

    private lateinit var viewModelTest: WeatherForecastViewModel

    private val weatherForecastEntityListMock = listOf(
        WeatherForecastEntity(
            112323232, TemperatureEntity(20f, 30f), 1000, 70, listOf(
                WeatherDescriptionEntity("light sun")
            )
        )
    )

    @Before
    fun init() {
        reset(getWeatherUseCaseMock)
        reset(weatherForecastObserver)
        viewModelTest = WeatherForecastViewModel(SavedStateHandle(), getWeatherUseCaseMock)
    }

    @Test
    fun getWeatherSuccessTest() = testCoroutineRule.runBlockingTest {
        `when`(getWeatherUseCaseMock.invoke("Ho Chi Minh")).thenReturn(flow {
            emit(
                weatherForecastEntityListMock
            )
        })
        viewModelTest.getWeather("Ho Chi Minh")
        viewModelTest.weatherForecastLiveData.observeForever(weatherForecastObserver)
        verify(getWeatherUseCaseMock).invoke("Ho Chi Minh")
        verify(weatherForecastObserver).onChanged(refEq(Resource.Processing()))
        verify(weatherForecastObserver).onChanged(refEq(Resource.Success(data = weatherForecastEntityListMock.toUiModel())))
        viewModelTest.weatherForecastLiveData.removeObserver(weatherForecastObserver)
    }
}
