package de.appcom.kmpplayground.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.activities.main.MainActivity
import de.appcom.kmpplayground.activities.main.MainActivityModule
import de.appcom.kmpplayground.annotations.ActivityScope

@Module
abstract class ActivityBindingModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentBindingsModule::class])
  abstract fun injectMainActivity(): MainActivity

}