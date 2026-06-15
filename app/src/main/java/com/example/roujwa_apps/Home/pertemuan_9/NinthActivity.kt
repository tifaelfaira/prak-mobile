package com.example.roujwa_apps.Home.pertemuan_9

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.roujwa_apps.databinding.ActivityNinthBinding
import com.example.roujwa_apps.utils.NotificationHelper
import com.example.roujwa_apps.utils.PermissionHelper
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.navigationIcon?.setTint(resources.getColor(android.R.color.white))
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        binding.chipGroupFilter.setOnCheckedStateChangeListener { _, _ -> }

        binding.btnLogin.setOnClickListener {
            val selectedChipId = binding.chipGroupFilter.checkedChipId

            if (selectedChipId != -1) {
                val chip = binding.chipGroupFilter.findViewById<Chip>(selectedChipId)
                val namaFilter = chip.text.toString()

                val intent = Intent(this, NinthActivity::class.java)

                // Memanggil Helper yang sudah berisi pengaturan ic_notification
                NotificationHelper.showNotification(
                    this,
                    "Verifikasi Berhasil",
                    "Kamu login dengan memverifikasi filter: $namaFilter",
                    intent
                )
            } else {
                Toast.makeText(this, "Silakan pilih salah satu filter terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}