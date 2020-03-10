package de.appcom.kmpplayground.notes.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.notes.R
import de.appcom.kmpplayground.notes.databinding.ActivityAddNoteBinding
import notes.domain.Note
import notes.presentation.AddNotePresenter
import notes.presentation.AddNoteView
import java.util.Date
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 2019-11-07.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class AddNoteActivity : DaggerAppCompatActivity(), AddNoteView {

  @Inject
  lateinit var presenter: AddNotePresenter

  lateinit var binding: ActivityAddNoteBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddNoteBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.addNotesToolbar)
    supportActionBar?.title = getString(R.string.notes_title_add)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.menu_add_note, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
      R.id.note_action_add -> addNote()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onResume() {
    super.onResume()
    binding.addNotesTitleTextinputedittext.requestFocus()
  }

  fun addNote() {
    presenter.addNote(
      Note(
        Date().time,
        binding.addNotesTitleTextinputedittext.text.toString(),
        binding.addNotesContentTextinputedittext.text.toString(),
        Date().time
      )
    )
  }

  override fun showError(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG)
      .show()
  }

  override fun onNoteAdded() {
    onBackPressed()
  }

}