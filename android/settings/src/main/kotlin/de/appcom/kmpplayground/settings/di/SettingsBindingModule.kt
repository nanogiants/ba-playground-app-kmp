package de.appcom.kmpplayground.settings.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.settings.presentation.SettingsActivity

@Module
abstract class SettingsBindingModule {

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [SettingsModule::class])
  abstract fun injectSettingsActivity(): SettingsActivity

}