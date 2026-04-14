package com.example.roujwa_apps.pertemuan_5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.roujwa_apps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar (Sesuai Modul Poin 5)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Detik"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 2. Konfigurasi WebView (Sesuai Modul Poin 2)
        binding.webView.apply {
            webViewClient = object : android.webkit.WebViewClient() {
                override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    binding.progressBar.visibility = android.view.View.GONE
                }
            }

            settings.javaScriptEnabled = true
            loadUrl("https://merdeka.com")

            // Agar Toolbar hide/show saat scroll web (Sesuai Modul Poin 5)
            setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) {
                    binding.appBar.setExpanded(false, true) // Sembunyikan
                } else if (scrollY < oldScrollY) {
                    binding.appBar.setExpanded(true, true) // Tampilkan
                }
            }
        }

        // 3. SOLUSI ERROR: Gunakan Callback untuk navigasi kembali (Anti-Error)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack() // Kembali ke halaman web sebelumnya
                } else {
                    finish() // Tutup activity jika tidak bisa back lagi di web
                }
            }
        })
    }

    // Support tombol back panah di toolbar atas
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}