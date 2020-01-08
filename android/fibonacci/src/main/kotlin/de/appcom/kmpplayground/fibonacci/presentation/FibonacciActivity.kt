package de.appcom.kmpplayground.fibonacci.presentation

import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import androidx.core.widget.doAfterTextChanged
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.fibonacci.R
import fibonacci.domain.Fibonacci
import fibonacci.domain.Timer
import fibonacci.domain.WorkHelper
import fibonacci.domain.runOnCallerThread
import fibonacci.domain.runWithCoroutinesOnUiDispatcher
import fibonacci.domain.start
import fibonacci.domain.stop
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_result_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_textinputedittext
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_textinputlayout
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_time1_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_time2_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_toolbar
import javax.inject.Inject

class FibonacciActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var workHelper: WorkHelper

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.fibonacci_activity)

    setSupportActionBar(fibonacci_toolbar)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = getString(R.string.fibonacci_title)

  }

  override fun onResume() {
    super.onResume()

    fibonacci_textinputedittext.doAfterTextChanged {
      val textString = it.toString()
      fibonacci_textinputlayout.error = ""
      if (textString.contains(Regex("^[0-9]+$"))) {
        val inputInt = textString.toInt()
        if (inputInt in (0..35)) {
          runFibonacci(textString.toInt())
        } else {
          fibonacci_textinputlayout.error = getString(R.string.fibonacci_error)
          resetUI()
        }
      } else {
        resetUI()
      }
    }
  }

  private fun resetUI() {
    fibonacci_result_textview.text = ""
    fibonacci_time1_textview.text = ""
    fibonacci_time2_textview.text = ""
  }

  private fun runFibonacci(n: Int) {
    if (n in (0..35)) {

      val task = Fibonacci()::calculate

      // 1 async task in activity
      Async(task, n).execute()

      // 2 coroutines in shared
      val timer2 = Timer().apply { start() }
      workHelper.runWithCoroutinesOnUiDispatcher(task, n,
        { result ->
          timer2.stop()
          fibonacci_time2_textview.text = "Coroutines ${timer2.endTime} ms"
        })
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

  private inner class Async(val task: (Int) -> Int, val n: Int) : AsyncTask<Int, Int, Int>() {

    var timer = Timer()

    override fun doInBackground(vararg p0: Int?): Int {
      timer.start()
      return workHelper.runOnCallerThread(task, n) ?: -1
    }

    override fun onPostExecute(result: Int?) {
      timer.stop()
      fibonacci_result_textview.text = "F($n) = ${result.toString()}"
      fibonacci_time1_textview.text = "Platform Api ${timer.endTime} ms"
    }
  }

}