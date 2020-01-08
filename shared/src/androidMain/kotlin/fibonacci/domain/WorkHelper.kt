package fibonacci.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class WorkHelper {

  actual fun getUIDispatcher(): CoroutineDispatcher {
    return Dispatchers.Main
  }
}
