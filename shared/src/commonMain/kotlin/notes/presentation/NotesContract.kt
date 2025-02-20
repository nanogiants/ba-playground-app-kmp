package notes.presentation

import notes.domain.Note

interface NotesView {
  fun showNotes(notes: List<Note>)
  fun showError(message: String)
  fun onNoteDeleted()
}

interface NotesPresenter {
  fun loadNotes()
  fun deleteNote(note: Note)
}