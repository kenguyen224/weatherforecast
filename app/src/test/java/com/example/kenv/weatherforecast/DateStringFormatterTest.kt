package com.example.kenv.weatherforecast

import com.example.kenv.weatherforecast.utils.formatDateString
import com.example.kenv.weatherforecast.utils.formatPercentage
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FormatterTest {
    @Test
    fun formatDateStringTest() {
        val epochTime = 1611637200L
        val actual = formatDateString(epochTime)
        assertEquals("Tue, 26 Jan 2021", actual)
    }

    @Test
    fun formatPercentageTest_70() {
        val actual = formatPercentage(70)
        assertEquals("70%", actual)
    }
}
