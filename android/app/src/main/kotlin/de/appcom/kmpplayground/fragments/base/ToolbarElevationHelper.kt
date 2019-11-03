package de.appcom.kmpplayground.fragments.base

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.RecyclerView
import de.appcom.kmpplayground.R
import timber.log.Timber

/**
 * Created by Fabian Heck on 2019-10-24.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class ToolbarElevationHelper(
  val context: Context,
  val toolbar: Toolbar
) : LifecycleObserver {

  private val elevation = context.resources.getDimension(R.dimen.toolbar_elevation)
  private val scrollOffsetY = context.resources.getDimension(R.dimen.toolbar_scrolloffset)
  private var viewTreeObserver: ViewTreeObserver? = null
  private var onScrollChangedListener: ViewTreeObserver.OnScrollChangedListener? = null

  /**
   * Add an adaptive toolbar behaviour.
   * @param view RecyclerView or ScrollableView
   */
  fun addAdaptiveElevation(view: View) {
    when (view) {
      is RecyclerView -> {
        onScrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
          toolbar.elevation =
            if (view.computeVerticalScrollOffset() > scrollOffsetY) elevation else 0f
        }
        viewTreeObserver = view.viewTreeObserver
        viewTreeObserver?.addOnScrollChangedListener(onScrollChangedListener)
        view.viewTreeObserver?.addOnScrollChangedListener(onScrollChangedListener)
      }
      is NestedScrollView -> {
        onScrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
          toolbar.elevation = if (view.scrollY > scrollOffsetY) elevation else 0f
        }
        viewTreeObserver = view.viewTreeObserver
        viewTreeObserver?.addOnScrollChangedListener(onScrollChangedListener)
        view.viewTreeObserver?.addOnScrollChangedListener(onScrollChangedListener)
      }
      else -> {
        Timber.e("View should be of type RecyclerView or NestedScrollView")
      }
    }
  }

  /**
   * Removes the adaptive toolbar elevation.
   * Call this method in onViewDestroyed() for Fragments or in onDestroy() for Activities
   */
  fun removeAdaptiveElevation() {
    toolbar.elevation = 0f
    viewTreeObserver?.apply {
      if (isAlive) {
        removeOnScrollChangedListener(onScrollChangedListener)
      }
    }
  }
}



