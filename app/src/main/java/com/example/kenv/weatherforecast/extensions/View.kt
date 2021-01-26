package com.example.kenv.weatherforecast.extensions

import android.view.View

/**
 * Created by KeNV on 27,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
fun View.show(isVisibility: Boolean) = if (isVisibility) {
    visibility = View.VISIBLE
} else {
    visibility = View.GONE
}
