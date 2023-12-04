package com.amonteiro.a2023_12_kampus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a2023_12_kampus.databinding.ActivityWeatherBinding
import java.util.Timer
import java.util.TimerTask


class WeatherActivity : AppCompatActivity() {


    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observe()

        binding.btLoad.setOnClickListener {

            if (binding.etCityName.text.toString().length < 3) {
                model.setError("Il faut 3 caractères")
            }

            model.loadData(binding.etCityName.text.toString())
        }
    }



    fun observe() {

        model.runInProgress.observe(this) {
            binding.progressBar.isVisible = it
        }

        model.data.observe(this) {
            binding.tvData.text = "il fait ${it?.main?.temp ?: "-"}° à ${it?.name ?: "-"}"
        }


        model.errorMessage.observe(this) {
            binding.tvError.text = it
        }
    }

}