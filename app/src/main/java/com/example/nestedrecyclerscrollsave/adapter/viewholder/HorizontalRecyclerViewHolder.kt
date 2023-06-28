package com.example.nestedrecyclerscrollsave.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerscrollsave.adapter.DiffItem
import com.example.nestedrecyclerscrollsave.adapter.HorizontalRecyclerAdapter
import com.example.nestedrecyclerscrollsave.adapter.savestate.LayoutManagerOwner
import com.example.nestedrecyclerscrollsave.adapter.savestate.RecyclerStateManager
import com.example.nestedrecyclerscrollsave.databinding.ItemHorizontalRecyclerBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class HorizontalRecyclerViewHolder(
    onInnerItemClick: () -> Unit,
    private val binding: ItemHorizontalRecyclerBinding,
    private val stateManager: RecyclerStateManager
) : RecyclerView.ViewHolder(binding.root), LayoutManagerOwner {

    override val layoutManager: RecyclerView.LayoutManager?
        get() = binding.horizontalList.layoutManager

    private val adapter = HorizontalRecyclerAdapter(
        onInnerItemClick
    )

    init {
        val lm = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
        binding.horizontalList.layoutManager = lm
        binding.horizontalList.adapter = adapter
        binding.horizontalList.addItemDecoration(
            MaterialDividerItemDecoration(
                binding.root.context,
                MaterialDividerItemDecoration.HORIZONTAL
            )
        )
        PagerSnapHelper().attachToRecyclerView(binding.horizontalList)
    }

    fun bind(item: HorizontalRecyclerItem) {
        stateManager["HorizontalRecyclerViewHolder:${item.id}"] = this
        stateManager.restoreViewStates()
        adapter.submitList(item.items)
    }
}

data class HorizontalRecyclerItem(
    val id: Long,
    val items: List<DiffItem>
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is HorizontalRecyclerItem
                && this.id == newItem.id

    override fun areContentsTheSame(newItem: DiffItem): Boolean {
        if (newItem !is HorizontalRecyclerItem) return false
        if (items.size != newItem.items.size) return false

        var itemsTheSame = true
        for (i in this.items.indices) {
            if (!items[i].areItemsTheSame(newItem.items[i])
                || !items[i].areContentsTheSame(newItem.items[i])
            ) {
                itemsTheSame = false
                break
            }
        }
        return itemsTheSame
    }
}