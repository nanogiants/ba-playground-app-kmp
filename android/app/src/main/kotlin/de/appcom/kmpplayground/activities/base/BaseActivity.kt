package de.appcom.kmpplayground.activities.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
abstract class BaseActivity(@LayoutRes val contentViewResId: Int) : DaggerAppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(contentViewResId)
  }

  override fun onDestroy() {
    super.onDestroy()
  }

}