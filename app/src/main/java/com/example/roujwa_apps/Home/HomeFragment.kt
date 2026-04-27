package com.example.roujwa_apps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import com.example.roujwa_apps.Home.pertemuan_2.SecondActivity
import com.example.roujwa_apps.Home.pertemuan_3.ThirdActivity
import com.example.roujwa_apps.Home.pertemuan_4.FourthActivity
import com.example.roujwa_apps.Home.pertemuan_5.FifthActivity
import com.example.roujwa_apps.Home.pertemuan_7.SeventhActivity
import com.example.roujwa_apps.R
import com.example.roujwa_apps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** Ganti menjadi versi binding */
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        binding.btnToSecond.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }
        binding.btnToThirth.setOnClickListener {
            val intent = Intent(requireContext(), ThirdActivity::class.java)
            startActivity(intent)

            // Tombol ke Pertemuan 4
            binding.btnToFourth.setOnClickListener {
                val intent = Intent(requireContext(), FourthActivity::class.java)
                intent.putExtra("nama", "Politeknik Caltex Riau")
                intent.putExtra("asal", "Rumbai")
                intent.putExtra("umur", 25)
                startActivity(intent)
            }

            // ---  INI UNTUK PERTEMUAN 5 ---
            binding.btnToFifth.setOnClickListener {
                val intent = Intent(requireContext(), FifthActivity::class.java)
                startActivity(intent)
            }

            binding.btnToSeventh.setOnClickListener {
                val intent = Intent(requireContext(), SeventhActivity::class.java)
                startActivity(intent)
            }

            binding.btnLogout.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah Anda yakin ingin keluar?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        dialog.dismiss()
                        sharedPref.edit {
                            clear()
                            apply()
                        }
                        requireActivity().finish()
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

