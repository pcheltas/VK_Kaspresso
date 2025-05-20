package com.example.myapplication.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityFlakyBinding

class FlakyCounterActivity : ComponentActivity() {
    private lateinit var binding: ActivityFlakyBinding
    private var counter = 0
    private var delay: Long = 5000
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlakyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFlucky.setOnClickListener {
            handler.postDelayed({
                counter++
                binding.counter.text = "Counter: $counter"
            }, delay)
        }
    }

}