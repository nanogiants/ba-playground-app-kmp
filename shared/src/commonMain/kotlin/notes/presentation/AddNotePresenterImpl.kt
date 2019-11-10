package notes.presentation

import kotlinx.coroutines.launch
import notes.data.NotesDataSource
import notes.models.Note

class AddNotePresenterImpl(
  val view: AddNoteView,
  val notesDataSource: NotesDataSource
) : BasePresenterImpl(), AddNotePresenter {
  override fun onCoroutinesException(throwable: Throwable) {
    view.showError("Exception handler coroutines called")
  }

  override fun addNote(note: Note) {
    launch {
      try {
        notesDataSource.addNote(note)
        view.onNoteAdded()
      } catch (e: Exception) {
        //TODO log Exception
        view.showError("Something went wrong")
      } finally {

      }
    }
  }
}