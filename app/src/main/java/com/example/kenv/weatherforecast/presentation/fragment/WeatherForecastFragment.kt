package com.example.kenv.weatherforecast.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kenv.weatherforecast.databinding.WeatherForecastFragmentBinding
import com.example.kenv.weatherforecast.extensions.show
import com.example.kenv.weatherforecast.presentation.activity.MainActivity
import com.example.kenv.weatherforecast.presentation.rcv.DividerItemDecoration
import com.example.kenv.weatherforecast.presentation.rcv.adapter.WeatherForecastAdapter
import com.example.kenv.weatherforecast.presentation.viewmodel.WeatherForecastViewModel
import com.example.kenv.weatherforecast.presentation.viewmodelfactory.WeatherForecastViewModelFactory
import com.example.kenv.weatherforecast.utils.Constant
import com.example.kenv.weatherforecast.utils.Resource
import javax.inject.Inject
import kotlinx.android.synthetic.main.weather_forecast_fragment.edtSearch
import kotlinx.android.synthetic.main.weather_forecast_fragment.progressBar
import kotlinx.android.synthetic.main.weather_forecast_fragment.rcvWeather
import kotlinx.android.synthetic.main.weather_forecast_fragment.tvError

class WeatherForecastFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: WeatherForecastViewModelFactory
    private val viewModel: WeatherForecastViewModel by viewModels { viewModelFactory }

    private lateinit var viewBinding: WeatherForecastFragmentBinding
    private lateinit var weatherForecastAdapter: WeatherForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = WeatherForecastFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherForecastAdapter = WeatherForecastAdapter()
        with(viewBinding.rcvWeather) {
            adapter = weatherForecastAdapter
            val context = requireContext()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context))
        }
        viewBinding.btnSearch.setOnClickListener { onClickGetWeather() }
        bindViewModel()
        viewBinding.edtSearch.requestFocus()
    }

    private fun onClickGetWeather() {
        val key = edtSearch.text.trim()
        val context = requireContext()
        when {
            key.isEmpty() -> {
                Toast.makeText(context, "Empty city name", Toast.LENGTH_SHORT).show()
            }
            key.length < Constant.MIN_LENGTH_KEY -> {
                Toast.makeText(context, "Please input at least 3 character", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                viewModel.getWeather(edtSearch.text.trim().toString())
            }
        }
    }

    private fun bindViewModel() = with(viewModel) {
        weatherForecastLiveData.observe(
            this@WeatherForecastFragment.viewLifecycleOwner,
            { resource ->
                when (resource) {
                    is Resource.Success -> {
                        tvError.show(false)
                        progressBar.show(false)
                        rcvWeather.show(true)
                        weatherForecastAdapter.setData(resource.data.listWeatherForecast)
                    }
                    is Resource.Error -> {
                        rcvWeather.show(false)
                        progressBar.show(false)
                        tvError.show(true)
                        tvError.text = resource.exception.message
                    }
                    is Resource.Processing -> {
                        rcvWeather.show(false)
                        tvError.show(false)
                        progressBar.show(resource.loading)
                    }
                }
            })
    }
}
