package com.amonteiro.a2023_12_kampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amonteiro.a2023_12_kampus.databinding.ActivityExoNavGraphBinding

class ExoNavGraphActivity : AppCompatActivity() {

    val binding by lazy { ActivityExoNavGraphBinding.inflate(layoutInflater) }
    //val model by lazy { ViewModelProvider(this)[ExoNavGraphBindingViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}