package de.appcom.kmpplayground.game.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.game.presentation.GameActivity

@Module
abstract class GameBindingsModule {
  //  @ActivityScope
  @ContributesAndroidInjector(modules = [GameModule::class])
  abstract fun injectNasaActivity(): GameActivity
}