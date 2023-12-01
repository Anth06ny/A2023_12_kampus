package com.amonteiro.a2023_12_kampus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a2023_12_kampus.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread


class WeatherActivity : AppCompatActivity() {


    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        println(model.data)

        model.data.observe(this) {
            binding.tvData.text = "il fait ${it?.main?.temp ?: "-"}° à ${it?.name ?: "-"}"
        }


        model.errorMessage.observe(this) {
            binding.tvError.text = it
        }

        binding.btLoad.setOnClickListener {

            binding.progressBar.isVisible = true

            val cityName = binding.etCityName.text.toString()

            thread {
                model.loadData(cityName)
                runOnUiThread {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

}