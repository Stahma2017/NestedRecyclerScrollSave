package com.example.nestedrecyclerscrollsave.adapter.savestate

import androidx.recyclerview.widget.RecyclerView.LayoutManager

interface LayoutManagerOwner {
    val layoutManager: LayoutManager?
}