package fibonacci

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.concurrent.freeze

actual class WorkHelper {

  private class Task<T1 : Any, T2 : Any>(
    val param: T1,
    val execute: (T1) -> T2
  )

  // There is no Dispatcher.Main for kotlin native
  private class MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(
      context: CoroutineContext,
      block: Runnable
    ) {
      dispatch_async(dispatch_get_main_queue()) { block.run() }
    }
  }

  actual fun <T1 : Any, T2 : Any> runOnBackgroundThread(
    task: (T1) -> T2,
    param: T1,
    onResult: (T2) -> Unit
  ) {
    val backgroundWorker = Worker.start()
    val future = backgroundWorker.execute(
      TransferMode.SAFE,
      { Task(param, task).freeze() },
      { workerTask ->
        workerTask.execute(workerTask.param)
      }
    )
    onResult(future.result)
  }

  actual fun getUIDispatcher(): CoroutineDispatcher {
    return MainDispatcher()
  }
}

