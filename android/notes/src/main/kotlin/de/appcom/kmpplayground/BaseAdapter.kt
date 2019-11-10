package de.appcom.kmpplayground

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by appcom interactive GmbH on 2019-11-07.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
abstract class BaseAdapter<T, V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {

  var itemList: MutableList<T> = mutableListOf()

  fun addItem(item: T) {
    itemList.add(item)
    notifyItemInserted(itemList.size)
  }

  fun addItem(
    item: T,
    position: Int
  ) {
    itemList.add(position, item)
    notifyItemInserted(position)
  }

  fun replaceItem(
    item: T,
    position: Int
  ) {
    removeItem(position)
    itemList.add(position, item)
    notifyItemChanged(position)
  }

  fun replaceAll(itemCollection: Collection<T>) {
    itemList = mutableListOf()
    itemList.addAll(0, itemCollection)
    notifyDataSetChanged()
  }

  fun removeItem(position: Int) {
    itemList.removeAt(position)
    notifyItemRemoved(position)
  }
}