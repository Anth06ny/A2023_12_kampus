package com.amonteiro.a2023_12_kampus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.a2023_12_kampus.databinding.ActivityWeatherBinding


class WeatherActivity : AppCompatActivity() {


    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}