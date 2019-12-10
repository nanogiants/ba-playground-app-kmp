package app.domain

import app.domain.UseCase.Identifier.FIBONACCI
import app.domain.UseCase.Identifier.NASA
import app.domain.UseCase.Identifier.NOTES
import app.domain.UseCase.Identifier.PIXELSORT
import app.domain.UseCase.Identifier.SETTINGS

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
  UseCase(
    NASA,
    "Nasa",
    "Description"
  ),
  UseCase(
    SETTINGS,
    "Settings",
    "Description"
  ),
  UseCase(
    NOTES,
    "Notes",
    "Description"
  ),
  UseCase(
    FIBONACCI,
    "Fibonacci",
    "Description"
  ),
  UseCase(
    PIXELSORT,
    "Pixelsort",
    "Description"
  )
)