package com.geeks.weather

import android.net.DnsResolver.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geeks.weather.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RetrofitService().api.getWeather("Bishkek")
            .enqueue(object : retrofit2.Callback<WeatherModel> {
                override fun onResponse(
                    call: Call<WeatherModel>,
                    response: Response<WeatherModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            binding.tvResult.text =
                                "temp: ${it.main.temp} \n feelLike: ${it.main.feel_like} \n City ${it.name}"
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                }

            })
    }
}