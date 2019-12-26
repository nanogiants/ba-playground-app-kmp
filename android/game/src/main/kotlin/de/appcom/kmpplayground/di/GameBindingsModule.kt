package de.appcom.kmpplayground.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.presentation.GameActivity

@Module
abstract class GameBindingsModule {
  //  @ActivityScope
  @ContributesAndroidInjector(modules = [GameModule::class])
  abstract fun injectNasaActivity(): GameActivity
}