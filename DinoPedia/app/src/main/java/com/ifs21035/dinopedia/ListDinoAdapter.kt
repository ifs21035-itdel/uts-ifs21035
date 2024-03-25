package com.ifs21035.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21035.dinopedia.databinding.ItemRowDinoBinding

class ListDinoAdapter(private val listDinosaur: ArrayList<Dinosaur>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dinosaur = listDinosaur[position]
        holder.binding.ivItemDino.setImageResource(dinosaur.icon)
        holder.binding.tvDinoName.text = dinosaur.name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinosaur[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDinosaur.size

    class ListViewHolder(var binding: ItemRowDinoBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dinosaur)
    }
}