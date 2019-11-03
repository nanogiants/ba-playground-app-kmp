package de.appcom.kmpplayground.fragments.about

import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class AboutPresenterImpl @Inject constructor(val view: AboutView) : AboutPresenter {

  override fun onResume() {
    super.onResume()
    Timber.d("About presenter is called")
  }
}