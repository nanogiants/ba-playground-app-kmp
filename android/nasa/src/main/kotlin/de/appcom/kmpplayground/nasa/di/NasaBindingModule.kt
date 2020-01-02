package de.appcom.kmpplayground.nasa.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.nasa.presentation.NasaActivity

@Module
abstract class NasaBindingModule {

  // @ActivityScope
  @ContributesAndroidInjector(modules = [NasaModule::class])
  abstract fun injectNasaActivity(): NasaActivity

}