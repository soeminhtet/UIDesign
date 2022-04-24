package com.codigo.uidesign.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigo.uidesign.databinding.RateItemBinding
import com.codigo.uidesign.dummy.rates

data class Rate(
    val title : String,
    val firstTitle : String = "",
    val showFirstTitle : Boolean = false
)

class RateAdapter : RecyclerView.Adapter<RateAdapter.ViewHolder>() {
    private var items = rates

    class ViewHolder(private val binding: RateItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Rate) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RateItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}