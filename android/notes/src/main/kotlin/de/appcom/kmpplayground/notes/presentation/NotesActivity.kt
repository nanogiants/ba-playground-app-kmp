package de.appcom.kmpplayground.notes.presentation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.notes.R
import de.appcom.kmpplayground.notes.databinding.ActivityNotesBinding
import de.appcom.kmpplayground.notes.presentation.NotesAdapter.NotesViewHolder
import notes.domain.Note
import notes.presentation.NotesPresenter
import notes.presentation.NotesView
import javax.inject.Inject

class NotesActivity : DaggerAppCompatActivity(), NotesView {

  @Inject
  lateinit var presenter: NotesPresenter

  private lateinit var binding: ActivityNotesBinding

  var adapter: NotesAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityNotesBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.notesToolbar)
    supportActionBar?.title = getString(R.string.notes_title)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    binding.notesFloatingactionbutton.setOnClickListener {
      startActivity(Intent(this, AddNoteActivity::class.java))
    }
  }

  override fun onResume() {
    super.onResume()
    adapter = NotesAdapter()
    binding.notesRecyclerview.layoutManager = LinearLayoutManager(this)
    binding.notesRecyclerview.adapter = adapter
    val itemTouchHelper = ItemTouchHelper(
      SwipeToDeleteCallback<Note, NotesViewHolder>(
        this,
        { position ->
          adapter?.let {
            val item = it.itemList[position]
            it.removeItem(position)
            presenter.deleteNote(item)
          }
        })
    )
    itemTouchHelper.attachToRecyclerView(binding.notesRecyclerview)
    presenter.loadNotes()

    binding.notesRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
          binding.notesFloatingactionbutton?.shrink()
        } else {
          binding.notesFloatingactionbutton?.extend()
        }
      }
    })
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun showNotes(notes: List<Note>) {
    adapter?.replaceAll(notes)
  }

  override fun showError(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG)
      .show()
  }

  override fun onNoteDeleted() {
    //
  }
}