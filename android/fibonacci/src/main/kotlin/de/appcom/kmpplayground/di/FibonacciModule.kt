package de.appcom.kmpplayground.di

import dagger.Module
import dagger.Provides
import fibonacci.domain.WorkHelper

//@Module(includes = [FibonacciModuleInnerBindings::class])
//abstract class FibonacciModule {
//
//  @Module
//  interface FibonacciModuleInnerBindings {
//
//  }
//
//  @Provides
//  @JvmStatic
//  fun provideWorkHelper(): WorkHelper {
//    return WorkHelper()
//  }
//
//}

@Module
object FibonacciModule {
  @JvmStatic
  @Provides
  fun provideWorkHelper() = WorkHelper()
}