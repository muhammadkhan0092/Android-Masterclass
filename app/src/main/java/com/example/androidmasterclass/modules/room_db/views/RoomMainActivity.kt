package com.example.androidmasterclass.modules.room_db.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmasterclass.databinding.RoomMainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomMainActivity : AppCompatActivity() {
    private var _binding : RoomMainActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = RoomMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}