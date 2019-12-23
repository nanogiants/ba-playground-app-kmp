package de.appcom.kmpplayground.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import de.appcom.kmpplayground.R
import kotlinx.android.synthetic.main.comparison_data_view.view.comparison_data_view_progress_textview
import kotlinx.android.synthetic.main.comparison_data_view.view.comparison_data_view_progressview
import kotlinx.android.synthetic.main.comparison_data_view.view.comparison_data_view_title_textview

class ComparisonDataView : FrameLayout {

  constructor(context: Context) : super(context) {
    init()
  }

  constructor(context: Context, attr: AttributeSet?) : super(context, attr) {
    init()
  }

  constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attr,
    defStyleAttr
  ) {
    init()
  }

  private fun init() {
    addView(inflate(context, R.layout.comparison_data_view, null))
  }

  fun setData(progress: Float, title: String) {
    comparison_data_view_title_textview.text = title
    comparison_data_view_progress_textview.text = "${(progress * 100).toInt()}%"
    comparison_data_view_progressview.progress = progress
    // animateSetLabel((progress * 100).toInt())
    // comparison_data_view_progressview.animateSetProgress(progress)
  }

  fun animateSetLabel(progress: Int) {
    ValueAnimator.ofInt(0, progress).apply {
      duration = 800
      addUpdateListener { updatedAnimation ->
        val updatedValue = updatedAnimation.animatedValue as Int
        comparison_data_view_progress_textview.text = "$updatedValue%"
      }
      // todo: cancel active animator?
      start()
    }
  }
}