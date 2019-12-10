package de.appcom.kmpplayground.fragments.usecases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.domain.UseCase
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.fragments.base.BaseAdapter
import de.appcom.kmpplayground.fragments.base.BaseViewHolder
import de.appcom.kmpplayground.fragments.usecases.UseCasesAdapter.UseCasesViewHolder
import kotlinx.android.synthetic.main.usecases_item_layout.view.usecases_item_description_textview
import kotlinx.android.synthetic.main.usecases_item_layout.view.usecases_item_title_textview

/**
 * Created by Fabian Heck on 2019-10-24.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class UseCasesAdapter(var onItemClickListener: (UseCase) -> Unit) :
  BaseAdapter<UseCase, UseCasesViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UseCasesViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.usecases_item_layout, parent, false)
    return UseCasesViewHolder(view)
  }

  override fun onBindViewHolder(holder: UseCasesViewHolder, position: Int) {
    holder.bindTo(itemList[position])
  }

  override fun getItemCount(): Int {
    return itemList.size
  }

  inner class UseCasesViewHolder(var view: View) : BaseViewHolder<UseCase>(view) {
    override fun bindTo(item: UseCase) {
      view.usecases_item_title_textview.text = item.title
      view.usecases_item_description_textview.text = item.description
      view.setOnClickListener {
        onItemClickListener.invoke(item)
      }
    }
  }
}
