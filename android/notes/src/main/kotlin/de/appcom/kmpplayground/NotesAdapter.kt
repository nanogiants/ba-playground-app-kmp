package de.appcom.kmpplayground

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.notes_item_layout.view.notes_content_textview
import kotlinx.android.synthetic.main.notes_item_layout.view.notes_title_textview
import notes.models.Note

/**
 * Created by appcom interactive GmbH on 2019-11-07.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class NotesAdapter : BaseAdapter<Note, NotesAdapter.NotesViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): NotesViewHolder {
    val view =
      LayoutInflater.from(parent.context)
          .inflate(R.layout.notes_item_layout, parent, false)
    return NotesViewHolder(view)
  }

  override fun getItemCount(): Int = itemList.size

  override fun onBindViewHolder(
    holder: NotesViewHolder,
    position: Int
  ) {
    holder.bindTo(itemList[position])
  }

  inner class NotesViewHolder(val view: View) : BaseViewHolder<Note>(view) {
    override fun bindTo(item: Note) {
      view.notes_title_textview.text = item.title
      view.notes_content_textview.text = item.content
    }
  }
}