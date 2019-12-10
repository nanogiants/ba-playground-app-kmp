package de.appcom.kmpplayground

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_notes.notes_floatingactionbutton
import kotlinx.android.synthetic.main.activity_notes.notes_recyclerview
import kotlinx.android.synthetic.main.activity_notes.notes_toolbar
import notes.data.NotesDataSourceImpl
import notes.data.NotesDatabaseHelper
import notes.domain.Note
import notes.presentation.NotesPresenter
import notes.presentation.NotesPresenterImpl
import notes.presentation.NotesView

class NotesActivity : AppCompatActivity(), NotesView {

  var adapter: NotesAdapter? = null
  var presenter: NotesPresenter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_notes)
    buildDependencies()
    setSupportActionBar(notes_toolbar)
    supportActionBar?.title = getString(R.string.notes_title)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    notes_floatingactionbutton.setOnClickListener {
      startActivity(Intent(this, AddNoteActivity::class.java))
    }
  }

  private fun buildDependencies() {
    // TODO use Dependency Injection
    presenter =
      NotesPresenterImpl(this, NotesDataSourceImpl(NotesDatabaseHelper(this).createDatabase()))
  }

  override fun onResume() {
    super.onResume()
    adapter = NotesAdapter()
    notes_recyclerview.layoutManager = LinearLayoutManager(this)
    notes_recyclerview.adapter = adapter
    val itemTouchHelper = ItemTouchHelper(
      SwipeToDeleteCallback<Note, NotesAdapter.NotesViewHolder>(
        this,
        { position ->
          adapter?.let {
            val item = it.itemList[position]
            it.removeItem(position)
            presenter?.deleteNote(item)
          }
        })
    )
    itemTouchHelper.attachToRecyclerView(notes_recyclerview)
    presenter?.loadNotes()

    notes_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
          notes_floatingactionbutton?.shrink()
        } else {
          notes_floatingactionbutton?.extend()
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