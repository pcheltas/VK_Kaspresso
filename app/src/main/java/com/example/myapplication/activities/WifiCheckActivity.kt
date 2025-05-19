package com.example.myapplication.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityWifiCheckBinding


class WifiCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWifiCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWifiCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheckWifi.setOnClickListener {
            if (isWifiConnected()) {
                binding.tvWifiStatus.text = "Wi-Fi connected"
            } else {
                binding.tvWifiStatus.text = "Wi-Fi not connected"
            }
        }
    }

    private fun isWifiConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)

    }
}