package fibonacci.domain

import android.os.Handler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.concurrent.thread

actual class WorkHelper {

  actual fun <T1: Any, T2: Any> runOnBackgroundThread(
    task: (T1) -> T2,
    param: T1,
    onResult: (T2) -> Unit
  ) {
    val handler = Handler()
    thread(true) {
      val result = task(param)
      handler.post {
        onResult(result)
      }
    }
  }

  actual fun getUIDispatcher(): CoroutineDispatcher {
    return Dispatchers.Main
  }
}


//fun logCurrentThreadInformation() {
//  val currentThread = Thread.currentThread()
//  val isUiThread = Looper.myLooper() == Looper.getMainLooper()
//  val name = currentThread.name
//  val parentName = currentThread.threadGroup.name
//  println("Currently on thread $name - isUiThread:$isUiThread parent:$parentName")
//}