package com.example.roujwa_apps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roujwa_apps.databinding.ActivityMainBinding
import com.example.roujwa_apps.Home.pertemuan_3.ThirdActivity
import com.example.roujwa_apps.Home.pertemuan_2.SecondActivity
import com.example.roujwa_apps.Home.pertemuan_4.FourthActivity
import com.example.roujwa_apps.Home.pertemuan_5.FifthActivity // Import ini
import com.example.roujwa_apps.Home.pertemuan_7.SeventhActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnToSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        binding.btnToThirth.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)

            // Tombol ke Pertemuan 4
            binding.btnToFourth.setOnClickListener {
                val intent = Intent(this, FourthActivity::class.java)
                intent.putExtra("nama", "Politeknik Caltex Riau")
                intent.putExtra("asal", "Rumbai")
                intent.putExtra("umur", 25)
                startActivity(intent)
            }

            // ---  INI UNTUK PERTEMUAN 5 ---
            binding.btnToFifth.setOnClickListener {
                val intent = Intent(this, FifthActivity::class.java)
                startActivity(intent)
            }

            binding.btnToSeventh.setOnClickListener {
                val intent = Intent(this, SeventhActivity::class.java)
                startActivity(intent)


                binding.btnLogout.setOnClickListener {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Konfirmasi")
                        .setMessage("Apakah Anda yakin ingin keluar?")
                        .setPositiveButton("Ya") { dialog, _ ->
                            dialog.dismiss()
                            sharedPref.edit {
                                clear()
                                apply()
                            }
                            finish()
                        }
                        .setNegativeButton("Batal") { dialog, _ ->
                            dialog.dismiss()
                            Log.e("Info Dialog", "Anda memilih Tidak!")
                        }
                        .show()
                }
            }
        }
    }
}