package fibonacci.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.concurrent.freeze

actual class WorkHelper {

  // There is no Dispatcher.Main for kotlin native
  private class MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(
      context: CoroutineContext,
      block: Runnable
    ) {
      dispatch_async(dispatch_get_main_queue()) { block.run() }
    }
  }

  actual fun getUIDispatcher(): CoroutineDispatcher {
    return MainDispatcher()
  }
}

