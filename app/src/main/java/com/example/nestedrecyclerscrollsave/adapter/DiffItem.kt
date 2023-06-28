package com.example.nestedrecyclerscrollsave.adapter

interface DiffItem {
    fun areItemsTheSame(newItem: DiffItem): Boolean
    fun areContentsTheSame(newItem: DiffItem): Boolean
}