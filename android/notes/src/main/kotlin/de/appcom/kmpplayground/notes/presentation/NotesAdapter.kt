package de.appcom.kmpplayground.notes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import de.appcom.kmpplayground.notes.databinding.NotesItemLayoutBinding
import de.appcom.kmpplayground.notes.presentation.NotesAdapter.NotesViewHolder
import notes.domain.Note

/**
 * Created by appcom interactive GmbH on 2019-11-07.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class NotesAdapter : BaseAdapter<Note, NotesViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): NotesViewHolder {
    return NotesViewHolder(
      NotesItemLayoutBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun getItemCount(): Int = itemList.size

  override fun onBindViewHolder(
    holder: NotesViewHolder,
    position: Int
  ) {
    holder.bindTo(itemList[position])
  }

  inner class NotesViewHolder(private val binding: NotesItemLayoutBinding) :
    BaseViewHolder<Note>(binding.root) {
    override fun bindTo(item: Note) {
      binding.notesTitleTextview.text = item.title
      binding.notesContentTextview.text = item.content
    }
  }
}