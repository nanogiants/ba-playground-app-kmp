package app

data class UseCase(
  val id: Identifier,
  val title: String,
//  val color: Color,
  val description: String = ""
) {
  enum class Identifier {
    PIXELSORT,
    NASA,
    NOTES,
    SETTINGS,
    FIBONACCI
  }
}

val appUseCases = listOf(
  UseCase(UseCase.Identifier.NASA, "Nasa", "Description"),
  UseCase(UseCase.Identifier.SETTINGS, "Settings", "Description"),
  UseCase(UseCase.Identifier.NOTES, "Notes", "Description"),
  UseCase(UseCase.Identifier.FIBONACCI, "Fibonacci", "Description"),
  UseCase(UseCase.Identifier.PIXELSORT, "Pixelsort", "Description")
)