package notes.domain

data class Note(
  var id: Long,
  var title: String,
  var content: String,
  var createdAt: Long
)