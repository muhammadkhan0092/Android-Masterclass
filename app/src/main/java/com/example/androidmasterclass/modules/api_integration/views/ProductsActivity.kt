package com.example.androidmasterclass.modules.api_integration.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmasterclass.databinding.ProductsActivityBinding

class ProductsActivity : AppCompatActivity() {
    private lateinit var _binding : ProductsActivityBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ProductsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}