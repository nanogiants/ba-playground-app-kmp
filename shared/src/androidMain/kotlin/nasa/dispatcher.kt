package nasa

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun getUIDispatcher(): CoroutineDispatcher {
  return Dispatchers.Main
}