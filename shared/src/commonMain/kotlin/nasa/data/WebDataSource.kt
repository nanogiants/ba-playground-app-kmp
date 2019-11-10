package nasa

interface WebDataSource {

  suspend fun getPictureOfTheDay(): PictureOfTheDay

}