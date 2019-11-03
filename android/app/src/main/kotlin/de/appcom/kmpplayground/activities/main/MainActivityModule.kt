package de.appcom.kmpplayground.activities.main

import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Named

/**
 * Created by Fabian Heck on 2019-10-24.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
@Module
abstract class MainActivityModule {

  @Binds
  @Named("Activity")
  abstract fun bindActivityContext(context: MainActivity): Context

}