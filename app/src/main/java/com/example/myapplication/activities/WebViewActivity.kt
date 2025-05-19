package com.example.myapplication.activities

import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webView = findViewById(R.id.webView)
        webView.settings.apply {
            domStorageEnabled = true
            loadsImagesAutomatically = true
            cacheMode = android.webkit.WebSettings.LOAD_DEFAULT
        }

        val url = intent.getStringExtra("url") ?: "https://example.com"


        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Toast.makeText(this@WebViewActivity, "Page loaded", Toast.LENGTH_SHORT).show()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Toast.makeText(
                    this@WebViewActivity,
                    "Error loading: ${error?.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.clearCache(true)
        webView.loadUrl(url)
    }
}