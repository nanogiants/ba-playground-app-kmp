package de.appcom.kmpplayground.fragments.comparison

import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class ComparisonPresenterImpl @Inject constructor(val view: ComparisonView) : ComparisonPresenter {

  override fun onResume() {
    super.onResume()
    Timber.d("ComparisonPresenterImpl presenter is called")
  }
}