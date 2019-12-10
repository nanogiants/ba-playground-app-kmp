package nasa.data

import nasa.domain.PictureOfTheDay

interface WebDataSource {

  suspend fun getPictureOfTheDay(): PictureOfTheDay

}