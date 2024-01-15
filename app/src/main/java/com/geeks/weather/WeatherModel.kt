package com.geeks.weather

data class WeatherModel(
    var main: MainModel,
    var wind: WindModel,
    var name: String,
)
data class WindModel(
    var speed: Double
)
data class MainModel(
    var temp: Double,
    var feel_like: Double
)
