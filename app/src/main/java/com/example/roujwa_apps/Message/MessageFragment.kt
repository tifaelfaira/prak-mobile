package com.example.roujwa_apps.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.roujwa_apps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // 🔥 FIX: ganti URL ke yang stabil
    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://i.pravatar.cc/150?img=1"),
        MessageModel("Budi", "Sudah makan?", "https://i.pravatar.cc/150?img=2"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://i.pravatar.cc/150?img=3"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://i.pravatar.cc/150?img=4"),
        MessageModel("Eka", "Nice job kemarin!", "https://i.pravatar.cc/150?img=5"),
        MessageModel("Fajar", "Lagi ngapain?", "https://i.pravatar.cc/150?img=6"),
        MessageModel("Gita", "Boleh minta tolong?", "https://i.pravatar.cc/150?img=7"),
        MessageModel("Hana", "Lihat email ya", "https://i.pravatar.cc/150?img=8"),
        MessageModel("Irfan", "Oke noted", "https://i.pravatar.cc/150?img=9"),
        MessageModel("Joko", "Sampai jumpa besok", "https://i.pravatar.cc/150?img=10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hilangkan ActionBar biar gak double
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        // Kosongkan title toolbar
        binding.toolbarMessage.title = ""

        // Adapter
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}