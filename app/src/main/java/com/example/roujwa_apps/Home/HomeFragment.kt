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
import androidx.lifecycle.lifecycleScope
import com.example.roujwa_apps.Home.pertemuan_10.TenthActivity
import com.example.roujwa_apps.Home.pertemuan_13.ThirteenthActivity
import com.example.roujwa_apps.Home.pertemuan_2.SecondActivity
import com.example.roujwa_apps.Home.pertemuan_3.ThirdActivity
import com.example.roujwa_apps.Home.pertemuan_4.FourthActivity
import com.example.roujwa_apps.Home.pertemuan_5.FifthActivity
import com.example.roujwa_apps.Home.pertemuan_7.SeventhActivity
import com.example.roujwa_apps.Home.pertemuan_9.NinthActivity
import com.example.roujwa_apps.R
import com.example.roujwa_apps.data.api.CatFactApiClient
import com.example.roujwa_apps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // Tombol-tombol pertemuan bawaan kamu tetap aman di sini
        binding.btnToSecond.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        binding.btnToThirth.setOnClickListener {
            val intent = Intent(requireContext(), ThirdActivity::class.java)
            startActivity(intent)
        }

        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("nama", "Politeknik Caltex Riau")
            intent.putExtra("asal", "Rumbai")
            intent.putExtra("umur", 25)
            startActivity(intent)
        }

        binding.btnToFifth.setOnClickListener {
            val intent = Intent(requireContext(), FifthActivity::class.java)
            startActivity(intent)
        }

        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            startActivity(intent)
        }

        binding.btnToNinth.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        binding.btnToTenth.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }

        binding.btnToThirteen.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
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

        // Panggil fungsi pencari fakta kucing saat fragment dibuat
        loadCatFact()

        // Tombol refresh panggil fungsi pencari fakta kucing
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }
    }

    private fun loadCatFact() {
        binding.tvCatFact.text = "Loading cat fact..."
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
                // 🛠️ FIX LOGCAT: Baris ini akan mencetak error asli ke Android Studio kamu!
                Log.e("ERROR_API", "Penyebab gagal load API: ${e.message}", e)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}