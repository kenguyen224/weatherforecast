package com.example.kenv.weatherforecast.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by KeNV on 26,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
private const val DATE_TEMPLATE = "%s, %s"
private const val DATE_PATTERN = "dd MMM yyyy"
private const val TEMPERATURE_TEMPLATE = "%.0f\u2103"

fun formatDateString(epochTimeSecond: Long): String {
    val sampleDate = SimpleDateFormat(DATE_PATTERN, Locale.ROOT)
    val date = Date(epochTimeSecond * 1000)
    val calendar = Calendar.getInstance().apply {
        time = date
    }
    val dayOfTheWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ROOT)
    return DATE_TEMPLATE.format(dayOfTheWeek, sampleDate.format(date))
}

fun formatTemperatureCelsius(temperature: Float): String = TEMPERATURE_TEMPLATE.format(temperature)

fun formatPercentage(value: Int): String {
    val percentFormat: NumberFormat = NumberFormat.getPercentInstance()
    return percentFormat.format(value.toFloat() / 100f)
}
