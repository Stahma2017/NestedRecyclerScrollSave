package com.example.nestedrecyclerscrollsave.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerscrollsave.adapter.DiffItem
import com.example.nestedrecyclerscrollsave.databinding.ItemBlankBinding

class BlankViewHolder(binding: ItemBlankBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {}

}

class BlackItem : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean = false
    override fun areContentsTheSame(newItem: DiffItem): Boolean = true
}