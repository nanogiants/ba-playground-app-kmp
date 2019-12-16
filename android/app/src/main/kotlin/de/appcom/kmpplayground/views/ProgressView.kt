package de.appcom.kmpplayground.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import de.appcom.kmpplayground.R
import kotlin.math.min

class ProgressView : View {

  private val maxBarHeight = 24.0f
  private val cornerRadius = 12.0f
  private val animationDuration: Long = 800

  var progress: Float = 0.0f
    set(value) {
      field = mapToRange(value)
      invalidate()
    }

  private lateinit var backgroundPaint: Paint
  private lateinit var barPaint: Paint

  private var actualBarHeight: Float = 0f
  private var actualBarWidth: Float = 0f

  private var barRect: RectF = RectF()
  private var backgroundRect: RectF = RectF()

  var backgroundColorRes: Int = R.color.colorProgressViewBackground
  var barColorRes: Int = R.color.colorProgressViewBar

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
    backgroundPaint = Paint()
    backgroundPaint.color = ContextCompat.getColor(context, backgroundColorRes)
    backgroundPaint.style = Paint.Style.FILL
    backgroundPaint.isAntiAlias = true

    barPaint = Paint()
    barPaint.color = ContextCompat.getColor(context, barColorRes)
    barPaint.style = Paint.Style.FILL
    barPaint.isAntiAlias = true
  }

  /**
   * How does the parent wants this view to behave
   */
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    val desiredWidth: Int = MeasureSpec.getSize(widthMeasureSpec)
    val desiredHeight: Int = maxBarHeight.toInt() + paddingTop + paddingBottom

    // resolveSize
    // if mode is MeasureSpec.UNSPECIFIED -> returns your calculated size (desiredSize)
    // if mode is MeasureSpec.EXACTLY -> returns the size in measureSpec (specSize)
    // if mode is MeasureSpec.AT_MOST -> returns min(desiredSize, specSize)
    val width = resolveSize(desiredWidth, widthMeasureSpec)
    val height = resolveSize(desiredHeight, heightMeasureSpec)

    // You need to set the dimension how big your view will be
    setMeasuredDimension(width, height)
  }

  /**
   * Called when view is first assigned a size and called again if the size of the view changes
   * Calculate dimensions, positions, ... here if possible and not in in draw
   */
  override fun onSizeChanged(width: Int, height: Int, oldwidth: Int, oldheight: Int) {
    super.onSizeChanged(width, height, oldwidth, oldheight)
    actualBarHeight = min(maxBarHeight, (height - paddingBottom - paddingTop).toFloat())
    actualBarWidth = (width - paddingEnd - paddingStart).toFloat()
    backgroundRect = RectF(0f, 0f, actualBarWidth, actualBarHeight)
    barRect = RectF(0f, 0f, actualBarWidth, actualBarHeight)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    if (canvas == null) {
      return
    }

    canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, backgroundPaint)

    barRect.right = actualBarWidth * progress
    canvas.drawRoundRect(barRect, cornerRadius, cornerRadius, barPaint)
  }

  fun animateSetProgress(value: Float) {
    val newProgress = mapToRange(value)
    val oldProgress = this.progress
    ValueAnimator.ofFloat(oldProgress, newProgress).apply {
      duration = animationDuration
      addUpdateListener { updatedAnimation ->
        progress = updatedAnimation.animatedValue as Float
        invalidate()
      }
      // todo: cancel active animator?
      start()
    }
  }

  private fun mapToRange(progress: Float): Float {
    return when {
      progress > 100 -> 100f
      progress < 0 -> 0f
      else -> progress
    }
  }

}
