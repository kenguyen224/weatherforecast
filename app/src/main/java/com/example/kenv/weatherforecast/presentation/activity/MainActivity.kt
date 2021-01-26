package com.example.kenv.weatherforecast.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.kenv.weatherforecast.R
import com.example.kenv.weatherforecast.databinding.MainActivityBinding
import com.example.kenv.weatherforecast.extensions.createComponent
import com.example.kenv.weatherforecast.presentation.di.component.WeatherForecastComponent

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: MainActivityBinding
    private lateinit var navController: NavController

    companion object {
        lateinit var component: WeatherForecastComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent()
        component.inject(this)
        viewBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        navController = findNavController(R.id.navHostFragment)
    }
}
