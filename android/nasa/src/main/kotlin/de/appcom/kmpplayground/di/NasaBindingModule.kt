package de.appcom.kmpplayground.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.presentation.NasaActivity

@Module
abstract class NasaBindingModule {

  // @ActivityScope
  @ContributesAndroidInjector(modules = [NasaModule::class])
  abstract fun injectNasaActivity(): NasaActivity

}