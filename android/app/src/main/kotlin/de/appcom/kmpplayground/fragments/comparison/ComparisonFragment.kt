package de.appcom.kmpplayground.fragments.comparison

import android.view.View
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.fragments.base.BaseFragment
import de.appcom.kmpplayground.fragments.base.BasePresenter
import kotlinx.android.synthetic.main.fragment_comparison.comparision_data_view_all
import kotlinx.android.synthetic.main.fragment_comparison.comparision_data_view_uc1
import kotlinx.android.synthetic.main.fragment_comparison.comparision_data_view_uc2
import kotlinx.android.synthetic.main.fragment_comparison.comparision_data_view_uc3
import kotlinx.android.synthetic.main.fragment_comparison.comparision_data_view_uc4
import kotlinx.android.synthetic.main.fragment_comparison.comparision_data_view_uc5
import kotlinx.android.synthetic.main.fragment_comparison.comparision_data_view_uc6
import kotlinx.android.synthetic.main.fragment_comparison.comparison_scrollview
import javax.inject.Inject

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class ComparisonFragment : BaseFragment(R.layout.fragment_comparison), ComparisonView {

  @Inject
  lateinit var presenter: ComparisonPresenter

  override val titleRes: Int
    get() = R.string.comparison_title

  override fun providePresenterToParent(): BasePresenter = presenter

  override val adaptiveToolbarScrollContainer: View
    get() = comparison_scrollview

  override fun onResume() {
    super.onResume()
    comparision_data_view_all.setData(0.29f, getString(R.string.compare_average))
    comparision_data_view_uc1.setData(0.47f, getString(R.string.nasa_title))
    comparision_data_view_uc2.setData(0.26f, getString(R.string.notes_title))
    comparision_data_view_uc3.setData(0.1f, getString(R.string.settings_title))
    comparision_data_view_uc4.setData(0.36f, getString(R.string.fibonacci_title))
    comparision_data_view_uc5.setData(0.0f, getString(R.string.pixelsort_title))
    comparision_data_view_uc6.setData(0.54f, getString(R.string.game_title))
  }
}