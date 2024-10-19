package com.example.retroweather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Weatherint {
    @GET("weather")
    fun getCurrentWeatherData(
        @Query("q") location: String?,
        @Query("appid") apiKey: String?
    ): Call<WeatherData?>?
}