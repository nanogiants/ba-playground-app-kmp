package nasa.presentation

import nasa.domain.PictureOfTheDay

interface NasaPresenter {
  fun initializeView()
}

interface NasaView {
  fun showPictureOfTheDay(pictureOfTheDay: PictureOfTheDay)
  fun setIsLoading(isLoading: Boolean)
  fun showError(message: String)
}

