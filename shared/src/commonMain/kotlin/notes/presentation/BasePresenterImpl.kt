package notes.presentation

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import notes.getUIDispatcher
import kotlin.coroutines.CoroutineContext

abstract class BasePresenterImpl : CoroutineScope {

  private val mainContext = getUIDispatcher()

  private val job = Job()

  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    onCoroutinesException(throwable)
  }

  override val coroutineContext: CoroutineContext
    get() = mainContext + job + exceptionHandler

  abstract fun onCoroutinesException(throwable: Throwable)

}