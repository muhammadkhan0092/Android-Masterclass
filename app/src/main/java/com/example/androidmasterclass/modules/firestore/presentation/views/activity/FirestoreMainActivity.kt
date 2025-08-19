package com.example.androidmasterclass.modules.firestore.presentation.views.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmasterclass.databinding.FirestoreMainActivityBinding
import com.example.androidmasterclass.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirestoreMainActivity : AppCompatActivity() {
    private lateinit var _binding : FirestoreMainActivityBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FirestoreMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}