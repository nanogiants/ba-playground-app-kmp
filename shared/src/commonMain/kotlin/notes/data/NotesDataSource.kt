package notes.data

import notes.domain.Note

interface NotesDataSource {

  suspend fun getAllNotes(): List<Note>

  suspend fun removeNote(note: Note)

  suspend fun addNote(note: Note)

}