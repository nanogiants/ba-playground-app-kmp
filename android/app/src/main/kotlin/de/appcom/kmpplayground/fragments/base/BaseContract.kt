package de.appcom.kmpplayground.fragments.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
interface BaseView : LifecycleOwner {

}

interface BasePresenter : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  open fun onCreate() {

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  open fun onResume() {

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  open fun onPause() {

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  open fun onDestroy() {

  }

}