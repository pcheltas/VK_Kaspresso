package com.example.myapplication.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val permissionGranted = permissions.entries.all { it.value }
            if (permissionGranted) {
                startActivity(Intent(this, PermissionActivity::class.java))
            } else {
                Toast.makeText(this@MainActivity, "Not enough permissions", Toast.LENGTH_SHORT)
                    .show()
            }
        }

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

        binding.btnRequestPermission.setOnClickListener {
            requestMicrophonePermission()
        }
    }

    private fun requestMicrophonePermission() {
        val permission = Manifest.permission.RECORD_AUDIO

        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(Intent(this, PermissionActivity::class.java))
        } else {
            permissionLauncher.launch(arrayOf(permission))
        }

    }
}