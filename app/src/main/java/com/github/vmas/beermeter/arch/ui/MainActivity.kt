package com.github.vmas.beermeter.arch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.vmas.beermeter.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
