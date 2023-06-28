package com.example.nestedrecyclerscrollsave.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.nestedrecyclerscrollsave.adapter.viewholder.InnerItem
import com.example.nestedrecyclerscrollsave.adapter.viewholder.InnerItemViewHolder
import com.example.nestedrecyclerscrollsave.databinding.ItemInnerBinding

class HorizontalRecyclerAdapter(
    private val onInnerItemClick: () -> Unit,
) :
    ListAdapter<DiffItem, InnerItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerItemViewHolder =
        InnerItemViewHolder(
            onInnerItemClick,
            ItemInnerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: InnerItemViewHolder, position: Int) {
        holder.bind(currentList[position] as InnerItem)
    }
}