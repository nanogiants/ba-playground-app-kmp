package de.appcom.kmpplayground.fibonacci.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.fibonacci.presentation.GameActivity

@Module
abstract class GameBindingsModule {
  //  @ActivityScope
  @ContributesAndroidInjector(modules = [GameModule::class])
  abstract fun injectNasaActivity(): GameActivity
}