package com.example.nestedrecyclerscrollsave.adapter

import androidx.recyclerview.widget.DiffUtil

class DiffCallback : DiffUtil.ItemCallback<DiffItem>() {

    override fun areItemsTheSame(oldItem: DiffItem, newItem: DiffItem): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: DiffItem, newItem: DiffItem): Boolean =
        oldItem.areContentsTheSame(newItem)
}