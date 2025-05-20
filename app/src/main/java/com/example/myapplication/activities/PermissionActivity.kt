package com.example.myapplication.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityPermissionSuccessBinding

class PermissionActivity : ComponentActivity() {
    private lateinit var binding: ActivityPermissionSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}