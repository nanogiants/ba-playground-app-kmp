package de.appcom.kmpplayground.di

import dagger.Binds
import dagger.Module
import de.appcom.kmpplayground.presentation.PixelsortActivity
import de.appcom.kmpplayground.presentation.PixelsortPresenter
import de.appcom.kmpplayground.presentation.PixelsortPresenterImpl
import de.appcom.kmpplayground.presentation.PixelsortView

@Module
interface PixelsortModule {

  @Binds
  fun providePixelsortPresenter(presenterImpl: PixelsortPresenterImpl): PixelsortPresenter

  @Binds
  fun providePixelsortView(activity: PixelsortActivity): PixelsortView

}
