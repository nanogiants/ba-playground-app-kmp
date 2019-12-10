package notes.presentation

import notes.domain.Note

interface AddNoteView {
  fun showError(message: String)
  fun onNoteAdded()
}

interface AddNotePresenter {
  fun addNote(note: Note)
}