package com.example.retroweather

import android.annotation.SuppressLint
import android.content.ClipDescription
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var cityNameTextView: TextView
    lateinit var temperatureTextView: TextView
    lateinit var weatherDescription: TextView
    lateinit var weatherIconImageView: ImageView
    lateinit var weatherButton: Button
    lateinit var cityEdit: EditText
    val apiKey = "e6f476b803465523e4def039f4b56cc1"


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cityNameTextView=findViewById(R.id.city_name)
        temperatureTextView=findViewById(R.id.temperature)
        weatherDescription=findViewById(R.id.weather_desc)
        weatherIconImageView=findViewById(R.id.imageView)
        cityEdit=findViewById(R.id.City_Edit)
        weatherButton=findViewById(R.id.button)
        weatherButton.setOnClickListener{
            loadWeatherData(cityEdit.text.toString())
        }
    }

    fun loadWeatherData(location:String){
        ApiClient.getapiservice().getCurrentWeatherData(location,apiKey)?.enqueue(object: Callback<WeatherData?>{
            override fun onResponse(call: Call<WeatherData?>, response: Response<WeatherData?>) {
                if(response.isSuccessful){
                    updateUI(response.body())
                }
            }

            override fun onFailure(call: Call<WeatherData?>, t: Throwable) {
                showError()
            }
        })
    }
    fun updateUI(weatherData: WeatherData?){
        cityNameTextView.text=weatherData?.name
        temperatureTextView.text=weatherData?.main?.temp.toString()
        weatherDescription.text=weatherData?.weather?.get(0)?.description
    }
    fun showError(){
        Toast.makeText(this@MainActivity, "Не удалось загрузить погодные данные", Toast.LENGTH_SHORT).show()
    }
}