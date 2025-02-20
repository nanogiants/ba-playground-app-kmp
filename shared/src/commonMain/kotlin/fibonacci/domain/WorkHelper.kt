package fibonacci.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

expect class WorkHelper() {

  fun getUIDispatcher(): CoroutineDispatcher

}

fun <T1: Any, T2: Any> WorkHelper.runOnCallerThread(task: (T1) -> T2, param: T1): T2 {
  return task(param)
}

fun <T1 : Any, T2 : Any> WorkHelper.runWithCoroutinesOnUiDispatcher(
  task: (T1) -> T2,
  param: T1,
  onResult: (T2) -> Unit
) {
  CoroutineScope(getUIDispatcher()).launch() {
    onResult(task(param))
  }
}
