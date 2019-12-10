package nasa.domain

data class PictureOfTheDay(
  val date: String,
  val explanation: String,
  val title: String,
  val url: String,
  val hasImage: Boolean
)