package de.appcom.kmpplayground

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by appcom interactive GmbH on 2019-11-07.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
abstract class BaseViewHolder<T>(val itemView: View) : RecyclerView.ViewHolder(itemView) {

  abstract fun bindTo(item: T)

}