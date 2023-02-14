package com.rate.manager.exchange.presentation.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rate.manager.exchange.R
import com.rate.manager.exchange.data.Da
import com.rate.manager.exchange.databinding.ActivityMyManExBinding

class MyManExActivity : AppCompatActivity() {
    lateinit var binding:ActivityMyManExBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyManExBinding.inflate(layoutInflater)
        setContentView(binding.root)
        seting()
        client()
        binding.root.loadUrl(intent.getStringExtra(Da.kk).toString())

    }

    @SuppressLint("SetJavaScriptEnabled")
    fun seting(){
        binding.root.settings.javaScriptEnabled = true
        binding.root.settings.domStorageEnabled = true
    }

    fun client(){
        binding.root.webViewClient = Client()
    }

    class Client:WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            if (request?.url.toString().startsWith("http"))
                view?.loadUrl(request?.url.toString())
            return true
        }
    }
}