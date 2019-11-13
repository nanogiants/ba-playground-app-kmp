package de.appcom.kmpplayground

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import fibonacci.Fibonacci
import fibonacci.WorkHelper
import fibonacci.runOnCallerThread
import fibonacci.runWithCoroutinesOnUiDispatcher
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_result_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_textinputedittext
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_textinputlayout
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_time1_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_time2_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_time3_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_toolbar

class FibonacciActivity : AppCompatActivity() {

  var workHelper: WorkHelper? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.fibonacci_activity)

    setSupportActionBar(fibonacci_toolbar)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = "fibonacci"

    workHelper = WorkHelper()
  }

  override fun onResume() {
    super.onResume()

    fibonacci_textinputedittext.doAfterTextChanged {
      val textString = it.toString()
      fibonacci_textinputlayout.error = ""
      if (textString.contains(Regex("^[0-9]+$"))) {
        val inputInt = textString.toInt()
        if (inputInt > 0 && inputInt <= 35) {
          runFibonacci(textString.toInt())
        } else {
          fibonacci_textinputlayout.error = "Bitte n zwischen 0 und 35"
          resetUI()
        }
      } else {
        resetUI()
      }
    }
  }

  fun resetUI() {
    fibonacci_result_textview.text = ""
    fibonacci_time1_textview.text = ""
    fibonacci_time2_textview.text = ""
    fibonacci_time3_textview.text = ""
  }

  fun runFibonacci(n: Int) {
    if (n > 0 && n <= 35) {

      val resultString: String = ""
      val task = Fibonacci()::calculate
      val param = n

      // 1
      // AsyncTask ??
      val startTime1 = SystemClock.elapsedRealtime()
      val result1 = workHelper?.runOnCallerThread(task, param)
      val endTime = SystemClock.elapsedRealtime() - startTime1
      fibonacci_result_textview.text = "F($n) = ${result1.toString()}"
      fibonacci_time1_textview.text = "Platform Api $endTime ms"

      // 2
      val startTime2 = SystemClock.elapsedRealtime()
      workHelper?.runOnBackgroundThread(task, param,
        { result ->
          val endTime = SystemClock.elapsedRealtime() - startTime2
          fibonacci_time2_textview.text = "Jvm Thread $endTime ms"
        }
      )

      // 3
      val startTime4 = SystemClock.elapsedRealtime()
      workHelper?.runWithCoroutinesOnUiDispatcher(task, param,
        { result ->
          val endTime = SystemClock.elapsedRealtime() - startTime4
          fibonacci_time3_textview.text = "Coroutines $endTime ms"
        })

      // RxJava/Kotlin/Swift/Native
      // in common kann man das in common verwenden?
      // kann man hier das normale threading von rxjava verwenden?
      // kann man das von swift aus verwenden oder sogar zu rxswift übersetzen

    } else {
      Log.d("d", "input not in ragne 0 to 35, stacktrace problem could happen ???")
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

}