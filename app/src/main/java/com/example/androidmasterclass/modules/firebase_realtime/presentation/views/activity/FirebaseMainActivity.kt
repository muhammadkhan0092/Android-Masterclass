package com.example.androidmasterclass.modules.firebase_realtime.presentation.views.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmasterclass.databinding.FirebaseMainActivityBinding
import com.example.androidmasterclass.databinding.FirestoreMainActivityBinding
import com.example.androidmasterclass.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirebaseMainActivity : AppCompatActivity() {
    private lateinit var _binding : FirebaseMainActivityBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FirebaseMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}