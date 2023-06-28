package com.example.nestedrecyclerscrollsave.model

import com.example.nestedrecyclerscrollsave.adapter.DiffItem
import com.example.nestedrecyclerscrollsave.adapter.viewholder.BlackItem
import com.example.nestedrecyclerscrollsave.adapter.viewholder.HorizontalRecyclerItem
import com.example.nestedrecyclerscrollsave.adapter.viewholder.InnerItem

private const val HEADING_BLANK_ITEM_COUNT = 3
private const val TAILED_BLANK_ITEM_COUNT = 9

object DataSets {

    val dataSet1: List<DiffItem>
        get() {
            val list = mutableListOf<DiffItem>()
            repeat(HEADING_BLANK_ITEM_COUNT) {
                list.add(BlackItem())
            }
            list.add(
                HorizontalRecyclerItem(
                    1L,
                    listOf(
                        InnerItem(1, "1", "dataSet1"),
                        InnerItem(2, "2", "dataSet1"),
                        InnerItem(3, "3", "dataSet1"),
                    )
                )
            )
            repeat(TAILED_BLANK_ITEM_COUNT) {
                list.add(BlackItem())
            }
            return list
        }

    val dataSet2: List<DiffItem>
        get() {
            val list = mutableListOf<DiffItem>()
            repeat(HEADING_BLANK_ITEM_COUNT) {
                list.add(BlackItem())
            }
            list.add(
                HorizontalRecyclerItem(
                    1L,
                    listOf(
                        InnerItem(1, "1", "dataSet2"),
                        InnerItem(2, "2", "dataSet2"),
                        InnerItem(3, "3", "dataSet2"),
                    )
                )
            )
            repeat(TAILED_BLANK_ITEM_COUNT) {
                list.add(BlackItem())
            }
            return list
        }


    val dataSet3: List<DiffItem>
        get() {
            val list = mutableListOf<DiffItem>()
            repeat(HEADING_BLANK_ITEM_COUNT) {
                list.add(BlackItem())
            }
            list.add(
                HorizontalRecyclerItem(
                    1L,
                    listOf(
                        InnerItem(1, "1", "dataSet3"),
                        InnerItem(2, "2", "dataSet3"),
                        InnerItem(3, "3", "dataSet3"),
                    )
                )
            )
            repeat(TAILED_BLANK_ITEM_COUNT) {
                list.add(BlackItem())
            }
            return list
        }
}