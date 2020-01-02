package de.appcom.kmpplayground.fibonacci.di

import android.content.Context
import dagger.Binds
import dagger.Module
import de.appcom.kmpplayground.PlaygroundApplication
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Fabian Heck on 2019-10-24.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
@Module
abstract class ApplicationModule {

  @Binds
  @Singleton
  @Named("Application")
  abstract fun bindApplicationContext(application: PlaygroundApplication): Context

}