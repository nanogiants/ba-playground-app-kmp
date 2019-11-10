package notes.data

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import de.appcom.kmpplayground.NotesDatabase

actual class NotesDatabaseHelper(private var context: Context) {

  actual fun createDatabase(): NotesDatabase {
    return NotesDatabase(AndroidSqliteDriver(NotesDatabase.Schema, context, "notes.db"))
  }
}
