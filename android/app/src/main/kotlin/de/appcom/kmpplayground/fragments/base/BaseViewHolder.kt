package de.appcom.kmpplayground.fragments.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Fabian Heck on 2019-10-24.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
abstract class BaseViewHolder<T>(val itemView: View) : RecyclerView.ViewHolder(itemView) {

  abstract fun bindTo(item: T)

}