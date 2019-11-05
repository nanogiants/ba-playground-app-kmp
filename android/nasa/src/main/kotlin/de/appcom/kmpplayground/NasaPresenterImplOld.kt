package de.appcom.kmpplayground

import PictureOfTheDay
import WebDataSource
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

//class NasaPresenterImplOld(
//    val view: NasaView,
//    val webDataSource: WebDataSource,
//    val activity: NasaActivity
//) : NasaPresenter {
//
//    var jobList: MutableList<Job> = mutableListOf()
//
//    private fun cancelableLaunch(outerBlock: suspend CoroutineScope.() -> Unit) {
//        jobList.add(activity.lifecycleScope.launch(block = outerBlock))
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    fun onResume() {
//        cancelableLaunch {
//            try {
//                view.setIsLoading(true)
//                val pictureOfTheDay: PictureOfTheDay = withContext(Dispatchers.IO) {
//                    webDataSource.getPictureOfTheDay()
//                }
//                view.showPictureOfTheDay(pictureOfTheDay)
//            } catch (e: Exception) {
//                Log.d("Nasa", "Exception: $e")
//            } finally {
//                view.setIsLoading(false)
//            }
//        }
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    fun onPause() {
//        jobList.forEach { job -> job.cancel() }
//    }
//
//}
//
