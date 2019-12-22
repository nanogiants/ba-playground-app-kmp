package de.appcom.kmpplayground.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import de.appcom.kmpplayground.presentation.NasaActivity
import nasa.data.WebDataSource
import nasa.data.WebDataSourceImpl
import nasa.presentation.NasaPresenter
import nasa.presentation.NasaPresenterImpl
import nasa.presentation.NasaView

@Module
object NasaModule {

  @Provides
  fun provideNasaView(activity: NasaActivity): NasaView {
    return activity
  }

  @Provides
  fun provideNasaPresenter(webDataSource: WebDataSource, view: NasaView): NasaPresenter {
    return NasaPresenterImpl(webDataSource, view)
  }

  @Provides
  fun provideWebDataSource(): WebDataSource {
    return WebDataSourceImpl()
  }
}