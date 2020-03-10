package nasa.nasa.data

object WebDataUtils {

  const val mediaTypeImage = "image"

  object NasaApi {
    const val baseUrl = "https://api.nasa.gov/planetary/"
    const val pictureOfTheDayPath = "apod"
    const val pictureOfTheDayUrl = "${baseUrl}/${pictureOfTheDayPath}"
  }

}