package de.appcom.kmpplayground.pixelsort.di

import dagger.Binds
import dagger.Module
import de.appcom.kmpplayground.pixelsort.presentation.PixelsortActivity
import de.appcom.kmpplayground.pixelsort.presentation.PixelsortPresenter
import de.appcom.kmpplayground.pixelsort.presentation.PixelsortPresenterImpl
import de.appcom.kmpplayground.pixelsort.presentation.PixelsortView

@Module
interface PixelsortModule {

  @Binds
  fun providePixelsortPresenter(presenterImpl: PixelsortPresenterImpl): PixelsortPresenter

  @Binds
  fun providePixelsortView(activity: PixelsortActivity): PixelsortView

}
