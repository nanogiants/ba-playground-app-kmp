package de.appcom.kmpplayground.pixelsort.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.pixelsort.presentation.PixelsortActivity

@Module
abstract class PixelsortBindingModule {

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [PixelsortModule::class])
  abstract fun injectPixelsortActivity(): PixelsortActivity

}