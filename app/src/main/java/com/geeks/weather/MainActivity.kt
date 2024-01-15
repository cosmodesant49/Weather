package com.geeks.weather

import android.net.DnsResolver.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.geeks.weather.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCity.setOnClickListener{
        RetrofitService().api.getWeather(binding.etCity.text.toString())
            .enqueue(object : retrofit2.Callback<WeatherModel> {
                override fun onResponse(
                    call: Call<WeatherModel>,
                    response: Response<WeatherModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            binding.tvCity.visibility = View.VISIBLE
                            binding.cardView.visibility = View.VISIBLE
                            binding.tvCity.text="${it.name}"
                            binding.tvTemperatureResult.text="${it.main.temp}"
                            binding.tvFeelLikeResult.text="${it.main.feel_like}"
                            binding.tvWindResult.text="${it.wind.speed}"
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                }

            })
            }
    }
}