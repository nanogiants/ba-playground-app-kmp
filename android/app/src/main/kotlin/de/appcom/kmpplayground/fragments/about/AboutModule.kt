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
