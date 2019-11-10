package notes.presentation

import notes.models.Note

interface AddNoteView {
  fun showError(message: String)
  fun onNoteAdded()
}

interface AddNotePresenter {
  fun addNote(note: Note)
}

