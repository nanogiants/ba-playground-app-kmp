package de.appcom.kmpplayground

import android.os.AsyncTask
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
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_result1_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_result2_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_result3_textview
import kotlinx.android.synthetic.main.fibonacci_activity.fibonacci_textinputedittext
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

//    fibonacciController = FibonacciControllerImpl()
    workHelper = WorkHelper()
  }

  override fun onResume() {
    super.onResume()

    fibonacci_textinputedittext.doAfterTextChanged {
      val textString = it.toString()
      if (textString.contains(Regex("^[0-9]+$"))) {
        Log.d("", "checking ok $textString")
        runFibonacci(textString.toInt())
      } else {
        Log.d("", "checking false $textString")
        fibonacci_result1_textview.text = ""
        fibonacci_result2_textview.text = ""
        fibonacci_result3_textview.text = ""
      }
    }

  }

  fun runFibonacci(n: Int) {
    if (n > 0 && n <= 35) {

      // 1
      // AsyncTask ??
      val task = Fibonacci()::calculate
      val param = n

      val result1 = workHelper?.runOnCallerThread(task, param)
      fibonacci_result1_textview.text = result1.toString()

      // 2
      val startTime2 = SystemClock.elapsedRealtime()
      workHelper?.runOnBackgroundThread(task, param,
        { result ->
          val endTime = SystemClock.elapsedRealtime() - startTime2
          fibonacci_result2_textview.text = "Ergebnis:" +result.toString() + " Berechnungszeit: " + endTime + " ms"
        }
      )

      // 3
      val startTime4 = SystemClock.elapsedRealtime()
      workHelper?.runWithCoroutinesOnUiDispatcher(task, param,
        { result ->
          val endTime = SystemClock.elapsedRealtime() - startTime4
        })

      // RxJava/Kotlin/Swift/Native
      // in common kann man das in common verwenden?
      // kann man hier das normale threading von rxjava verwenden?

      // RxJAva/Kotlin/Swift/NAtive
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