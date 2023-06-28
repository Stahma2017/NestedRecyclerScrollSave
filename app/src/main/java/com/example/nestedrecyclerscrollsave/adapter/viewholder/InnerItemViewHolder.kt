package com.example.nestedrecyclerscrollsave.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerscrollsave.adapter.DiffItem
import com.example.nestedrecyclerscrollsave.databinding.ItemInnerBinding
import com.google.android.material.snackbar.Snackbar

class InnerItemViewHolder(
    onInnerItemClick: () -> Unit,
    private val binding: ItemInnerBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onInnerItemClick() }
    }

    fun bind(item: InnerItem) {
        binding.label.text = item.text
        binding.subtitle.text = item.source
    }
}

data class InnerItem(
    val id: Long,
    val text: String,
    val source: String
) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is InnerItem
                && this.id == newItem.id

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is InnerItem
                && this == newItem
}