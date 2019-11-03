package de.appcom.kmpplayground

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import de.appcom.kmpplayground.di.DaggerApplicationComponent
import timber.log.Timber

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class PlaygroundApplication : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      enableTimberLogging()
    }
  }

  private fun enableTimberLogging() {
    Timber.plant(object : Timber.DebugTree() {
      override fun createStackElementTag(element: StackTraceElement): String? {
        return "${super.createStackElementTag(element)}, method ${element.methodName} :: line ${element.lineNumber}"
      }
    })
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerApplicationComponent.factory().create(this)
  }

}