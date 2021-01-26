package com.example.kenv.weatherforecast.utils.lazyinit

/**
 * Created by KeNV on 25,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
fun <T> resettableLazy(manager: ResettableLazyManager, init: () -> T): ResettableLazy<T> {
    return ResettableLazy(manager, init)
}

fun resettableManager(): ResettableLazyManager = ResettableLazyManager()
