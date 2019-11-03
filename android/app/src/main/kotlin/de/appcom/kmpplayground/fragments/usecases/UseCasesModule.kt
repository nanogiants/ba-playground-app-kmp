package de.appcom.kmpplayground.fragments.usecases

import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {

  @Binds
  fun provideUseCasesPresenter(presenterImpl: UseCasesPresenterImpl): UseCasesPresenter

  @Binds
  fun provideUseCasesView(fragment: UseCasesFragment): UseCasesView
}
