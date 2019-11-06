interface WebDataSource {

    suspend fun getPictureOfTheDay(): PictureOfTheDay

}