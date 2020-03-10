package de.appcom.kmpplayground.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import de.appcom.kmpplayground.activities.main.MainActivity
import de.appcom.kmpplayground.databinding.FragmentAboutBinding
import kotlinx.android.synthetic.main.activity_main.main_toolbar

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
abstract class BaseFragment : DaggerFragment() {

  @LayoutRes open val titleRes: Int = -1

  open val adaptiveToolbarScrollContainer: View? = null

  var toolbarElevationHelper: ToolbarElevationHelper? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return contentView(inflater, container, savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    lifecycle.addObserver(providePresenterToParent())
    configureParentToolbar()
  }

  private fun configureParentToolbar() {
    (activity as MainActivity).configureToolbar(titleRes = titleRes)
    adaptiveToolbarScrollContainer?.let { scrollableView ->
      toolbarElevationHelper =
        ToolbarElevationHelper(requireContext(), (activity as MainActivity).main_toolbar)
      toolbarElevationHelper?.addAdaptiveElevation(scrollableView)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    toolbarElevationHelper?.removeAdaptiveElevation()
  }

  // allows set presenter as lifecycle observer here in the BaseFragment
  // instead of doing this in each specific fragment class
  abstract fun providePresenterToParent(): BasePresenter

  abstract fun contentView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View
}