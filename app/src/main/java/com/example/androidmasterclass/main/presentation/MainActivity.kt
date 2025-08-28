package com.example.androidmasterclass.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmasterclass.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding : MainActivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}