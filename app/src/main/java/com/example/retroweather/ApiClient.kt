package com.example.retroweather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    companion object{
        val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        fun getapiservice():
                Weatherint{
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Weatherint::class.java)
        }

    }


}