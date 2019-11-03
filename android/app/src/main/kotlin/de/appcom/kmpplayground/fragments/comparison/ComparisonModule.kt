package de.appcom.kmpplayground.fragments.comparison

import dagger.Binds
import dagger.Module

@Module
interface ComparisonModule {

  @Binds
  fun provideComparisonPresenter(presenterImpl: ComparisonPresenterImpl): ComparisonPresenter

  @Binds
  fun provideComparisonView(fragment: ComparisonFragment): ComparisonView
}

//@Module(includes = [ComparisonModuleBinds::class])
//abstract class ComparisonModule {
//
//  @Module
//  interface ComparisonModuleBinds {
//    @Binds
//    fun provideComparisonView(fragment: ComparisonFragment): ComparisonView
//  }
//
//  @Provides
//  fun provideComparisonPresenter(presenterImpl: ComparisonPresenterImpl): ComparisonPresenter {
//    presenterImpl.view.lifecycle.addObserver(presenterImpl)
//    return presenterImpl
//  }
//}