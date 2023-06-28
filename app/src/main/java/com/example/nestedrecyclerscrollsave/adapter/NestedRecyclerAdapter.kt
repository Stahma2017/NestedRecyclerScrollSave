package com.example.nestedrecyclerscrollsave.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerscrollsave.R
import com.example.nestedrecyclerscrollsave.adapter.savestate.LayoutManagerOwner
import com.example.nestedrecyclerscrollsave.adapter.savestate.RecyclerStateManager
import com.example.nestedrecyclerscrollsave.adapter.viewholder.BlackItem
import com.example.nestedrecyclerscrollsave.adapter.viewholder.BlankViewHolder
import com.example.nestedrecyclerscrollsave.adapter.viewholder.HorizontalRecyclerItem
import com.example.nestedrecyclerscrollsave.adapter.viewholder.HorizontalRecyclerViewHolder
import com.example.nestedrecyclerscrollsave.databinding.ItemBlankBinding
import com.example.nestedrecyclerscrollsave.databinding.ItemHorizontalRecyclerBinding

class NestedRecyclerAdapter(
    private val onInnerItemClick: () -> Unit,
    private val stateManager: RecyclerStateManager
) : ListAdapter<DiffItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is LayoutManagerOwner) {
            stateManager.saveViewStates()
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is HorizontalRecyclerItem -> R.layout.item_horizontal_recycler
            is BlackItem -> R.layout.item_blank
            else -> throw IllegalArgumentException("unknown item ${currentList[position].javaClass.simpleName}")
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val viewHolder = when (viewType) {
            R.layout.item_horizontal_recycler -> HorizontalRecyclerViewHolder(
                onInnerItemClick,
                ItemHorizontalRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                stateManager
            )
            R.layout.item_blank -> BlankViewHolder(
                ItemBlankBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("unknown viewType $viewType")
        }

        return viewHolder
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_horizontal_recycler -> (holder as HorizontalRecyclerViewHolder).bind(
                currentList[position] as HorizontalRecyclerItem
            )
        }
    }
}

