package com.amonteiro.a2023_12_kampus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.a2023_12_kampus.databinding.ActivityMainBinding
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    //Permet d'instancier les composants graphiques à la 1er utilisation
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        println("MainActivity.onCreate")

        //on écoute les clics des boutons
        binding.btValidate.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }
        binding.btCancel.setOnClickListener {

            binding.progressBar2.isVisible = true

            thread {
                //binding.et.setText(WeatherAPI.loadData())
                println("Thread fini")

                runOnUiThread {
                    binding.progressBar2.isVisible = false
                }


            }

            binding.et.setText("...")
        }
    }

}