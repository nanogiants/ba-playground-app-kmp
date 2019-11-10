package notes.data

import de.appcom.kmpplayground.NoteQueries
import de.appcom.kmpplayground.NotesDatabase
import notes.models.Note

expect class NotesDatabaseHelper {

  fun createDatabase(): NotesDatabase

}

class NotesDataSourceImpl(notesDatabase: NotesDatabase) : NotesDataSource {

  val notesQueries: NoteQueries = notesDatabase.noteQueries

  override suspend fun getAllNotes(): List<Note> {
    return notesQueries.allNotes()
        .executeAsList()
        .map { entity -> Note(entity.id, entity.title, entity.content, entity.created_at) }
  }

  override suspend fun removeNote(note: Note) {
    notesQueries.removeNote(note.id)
  }

  override suspend fun addNote(note: Note) {
    notesQueries.addNote(note.id, note.title, note.content, note.createdAt)
  }

}