package notes.data

import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import de.appcom.kmpplayground.NotesDatabase

actual class NotesDatabaseHelper {

  actual fun createDatabase(): NotesDatabase {
    return NotesDatabase(NativeSqliteDriver(NotesDatabase.Schema, "notes.db"))
  }

}
