package com.example.roujwa_apps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.roujwa_apps.Home.HomeFragment
import com.example.roujwa_apps.Message.MessageFragment
import com.example.roujwa_apps.More.MoreFragment
import com.example.roujwa_apps.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Set Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.elevation = 0f

        // 2. PERBAIKAN PADDING: Hapus v.setPadding supaya gelombang ungu bisa "mentok" ke atas
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Jangan kasih padding top di sini agar View gelombang bisa full ke atas status bar
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }

        // Default awal
        if (savedInstanceState == null) {
            updateToolbar("Home")
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    updateToolbar("Home")
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.message -> {
                    updateToolbar("Message")
                    replaceFragment(MessageFragment())
                    true
                }
                R.id.more -> {
                    updateToolbar("More")
                    replaceFragment(MoreFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Fungsi pembantu buat ganti judul biar gak repot
    fun updateToolbar(title: String) {
        supportActionBar?.title = title
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}