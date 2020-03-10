package de.appcom.kmpplayground.fibonacci.presentation

import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import androidx.core.widget.doAfterTextChanged
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.fibonacci.R
import de.appcom.kmpplayground.fibonacci.databinding.ActivityFibonacciBinding
import fibonacci.domain.Fibonacci
import fibonacci.domain.Timer
import fibonacci.domain.WorkHelper
import fibonacci.domain.runOnCallerThread
import fibonacci.domain.runWithCoroutinesOnUiDispatcher
import fibonacci.domain.start
import fibonacci.domain.stop
import javax.inject.Inject

class FibonacciActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var workHelper: WorkHelper

  private lateinit var binding: ActivityFibonacciBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityFibonacciBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.fibonacciToolbar)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = getString(R.string.fibonacci_title)
  }

  override fun onResume() {
    super.onResume()

    binding.fibonacciTextinputedittext.doAfterTextChanged {
      val textString = it.toString()
      binding.fibonacciTextinputlayout.error = ""
      if (textString.contains(Regex("^[0-9]+$"))) {
        val inputInt = textString.toInt()
        if (inputInt in (0..35)) {
          runFibonacci(textString.toInt())
        } else {
          binding.fibonacciTextinputlayout.error = getString(R.string.fibonacci_error)
          resetUI()
        }
      } else {
        resetUI()
      }
    }
  }

  private fun resetUI() {
    binding.fibonacciResultTextview.text = ""
    binding.fibonacciTime1Textview.text = ""
    binding.fibonacciTime2Textview.text = ""
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
          binding.fibonacciTime2Textview.text = "Coroutines ${timer2.endTime} ms"
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
      binding.fibonacciResultTextview.text = "F($n) = ${result.toString()}"
      binding.fibonacciTime1Textview.text = "Platform Api ${timer.endTime} ms"
    }
  }

}