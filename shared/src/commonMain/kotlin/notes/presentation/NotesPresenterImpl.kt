package notes.presentation

import kotlinx.coroutines.launch
import notes.data.NotesDataSource
import notes.domain.Note

class NotesPresenterImpl(
  val view: NotesView,
  val notesDataSource: NotesDataSource
) :
    BasePresenterImpl(), NotesPresenter {

  override fun onCoroutinesException(throwable: Throwable) {
    view.showError("Exception handler coroutines called")
  }

  override fun loadNotes() {
    launch() {
      try {
        view.showNotes(notesDataSource.getAllNotes())
      } catch (e: Exception) {
        //TODO log Exception
        view.showError("Something went wrong")
      } finally {

      }
    }
  }

  override fun deleteNote(note: Note) {
    launch() {
      try {
        notesDataSource.removeNote(note)
        view.onNoteDeleted()
      } catch (e: Exception) {
        //TODO log Exception
        view.showError("Something went wrong")
      } finally {

      }
    }
  }
}