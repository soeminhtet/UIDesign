package com.codigo.uidesign.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigo.uidesign.databinding.RoomItemBinding
import com.codigo.uidesign.dummy.rooms

data class Room(
    val image : Int,
    val type : String,
    val description : String,
    val time : String,
    val price : String
)

class RoomAdapter : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    private var items = rooms

    class ViewHolder(private val binding: RoomItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Room) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoomItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}