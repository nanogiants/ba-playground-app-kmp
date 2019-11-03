package de.appcom.kmpplayground.fragments.about

import dagger.Binds
import dagger.Module

@Module
interface AboutModule {

  @Binds
  fun provideAboutPresenter(presenterImpl: AboutPresenterImpl): AboutPresenter

  @Binds
  fun provideAboutView(fragment: AboutFragment): AboutView
}

//@Module(includes = [AboutModuleBinds::class])
//class AboutModule {
//
//  @Module
//  interface AboutModuleBinds {
//    @Binds
//    fun provideAboutView(fragment: AboutFragment): AboutView
//  }
//
//  @Provides
//  fun provideAboutPresenter(presenterImpl: AboutPresenterImpl): AboutPresenter {
//    presenterImpl.view.lifecycle.addObserver(presenterImpl)
//    return presenterImpl
//  }
//}