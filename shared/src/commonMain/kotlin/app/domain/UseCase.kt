package app.domain

import app.domain.UseCase.Identifier.FIBONACCI
import app.domain.UseCase.Identifier.NASA
import app.domain.UseCase.Identifier.NOTES
import app.domain.UseCase.Identifier.PIXELSORT
import app.domain.UseCase.Identifier.SETTINGS

data class UseCase(
  val id: Identifier,
  val titleRes: Int,
//  val color: Color,
  val descriptionRes: Int
) {
  enum class Identifier {
    PIXELSORT,
    NASA,
    NOTES,
    SETTINGS,
    FIBONACCI
  }
}