package nasa

import kotlinx.serialization.Serializable

@Serializable
data class PictureOfTheDayWebEntity(
  val date: String,
  val explanation: String,
  val title: String,
  val url: String,
  val service_version: String,
  val media_type: String,
  val hdurl: String,
  val copyright: String = ""
)