package de.appcom.kmpplayground.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.R
import kotlinx.android.synthetic.main.activity_add_note.add_notes_content_textinputedittext
import kotlinx.android.synthetic.main.activity_add_note.add_notes_title_textinputedittext
import kotlinx.android.synthetic.main.activity_add_note.add_notes_toolbar
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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_note)
    setSupportActionBar(add_notes_toolbar)
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
    add_notes_title_textinputedittext.requestFocus()
  }

  fun addNote() {
    presenter.addNote(
      Note(
        Date().time,
        add_notes_title_textinputedittext.text.toString(),
        add_notes_content_textinputedittext.text.toString(),
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