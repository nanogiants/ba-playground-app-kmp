package de.appcom.kmpplayground.fragments.comparison

import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.fragments.base.BaseFragment
import de.appcom.kmpplayground.fragments.base.BasePresenter
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
}