package com.example.roujwa_apps.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.roujwa_apps.R
import com.example.roujwa_apps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // 🔥 reuse view biar lebih optimal
        val binding: ItemMessageBinding
        val view: View

        if (convertView == null) {
            binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ItemMessageBinding
            view = convertView
        }

        val data = messages[position]

        // Set text
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // 🔥 Glide FIX + DEBUG
        Glide.with(context)
            .load(data.avatarUrl)
            .placeholder(R.drawable.circle_bg) // sementara loading
            .error(android.R.drawable.ic_menu_close_clear_cancel) // ❗ kalau gagal load
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .into(binding.avatarImg)

        // Click
        view.setOnClickListener {
            Snackbar.make(
                it,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}