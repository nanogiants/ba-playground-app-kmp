package notes

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext

class MainDispatcher : CoroutineDispatcher() {
  override fun dispatch(
    context: CoroutineContext,
    block: Runnable
  ) {
    dispatch_async(dispatch_get_main_queue()) { block.run() }
  }
}

actual fun getUIDispatcher(): CoroutineDispatcher {
//    return Dispatchers.Main
  return MainDispatcher()
}