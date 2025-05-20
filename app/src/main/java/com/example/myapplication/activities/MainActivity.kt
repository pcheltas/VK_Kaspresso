package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTodoList.setOnClickListener {
            startActivity(Intent(this, NewTaskActivity::class.java))
        }

        binding.btnCheckWifi.setOnClickListener {
            startActivity(Intent(this, WifiCheckActivity::class.java))
        }

        binding.btnFlucky.setOnClickListener {
            startActivity(Intent(this, FlakyCounterActivity::class.java))
        }
    }
}