package de.appcom.kmpplayground.fibonacci.di

import dagger.Module
import dagger.Provides
import fibonacci.domain.WorkHelper


@Module
object FibonacciModule {
  @JvmStatic
  @Provides
  fun provideWorkHelper() = WorkHelper()
}