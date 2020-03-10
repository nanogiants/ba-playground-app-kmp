package nasa.presentation

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import nasa.data.WebDataSource
import nasa.getUIDispatcher
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

class NasaPresenterImpl(
  val webDataSource: WebDataSource,
  val view: NasaView
) : CoroutineScope, NasaPresenter {

  private val mainContext = getUIDispatcher()

  private val job = Job()

  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    view.showError("Exception handler coroutines called")
  }

  override val coroutineContext: CoroutineContext
    get() = mainContext + job + exceptionHandler

  override fun initializeView() {
    launch() {
      try {
        view.setIsLoading(true)
        val pictureOfTheDay = webDataSource.getPictureOfTheDay()
        view.showPictureOfTheDay(pictureOfTheDay)
      } catch (e: Exception) {
        view.showError("Something went wrong")
        view.showError(e)
      } finally {
        view.setIsLoading(false)
      }
    }
  }
}
