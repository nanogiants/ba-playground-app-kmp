package de.appcom.kmpplayground.fragments.usecases

import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class UseCasesPresenterImpl @Inject constructor(val view: UseCasesView) : UseCasesPresenter {

  override fun onResume() {
    super.onResume()
    Timber.d("UseCasesPresenterImpl presenter is called")
  }
}