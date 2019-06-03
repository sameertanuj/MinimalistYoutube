package com.samoana.minimalistyoutube.utils

import androidx.recyclerview.widget.DiffUtil
import com.samoana.minimalistyoutube.data.Item

class ResultsDiffUtil(private val oldItems: List<Item>, private val newItems: List<Item>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}