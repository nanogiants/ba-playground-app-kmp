package de.appcom.kmpplayground.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.presentation.SettingsActivity

@Module
abstract class SettingsBindingModule {

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [SettingsModule::class])
  abstract fun injectSettingsActivity(): SettingsActivity

}