package fibonacci.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class WorkHelper {

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