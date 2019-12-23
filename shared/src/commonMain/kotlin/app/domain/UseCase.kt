package app.domain

data class UseCase(
  val id: Identifier,
  val titleRes: Int,
  val colorString: String,
  val descriptionRes: Int,
  val iconRes: Int
) {
  enum class Identifier {
    PIXELSORT,
    NASA,
    NOTES,
    SETTINGS,
    FIBONACCI
  }
}