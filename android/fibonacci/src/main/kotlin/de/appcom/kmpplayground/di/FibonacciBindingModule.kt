package de.appcom.kmpplayground.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.presentation.FibonacciActivity

@Module
abstract class FibonacciBindingModule {

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [FibonacciModule::class])
  abstract fun injectMainActivity(): FibonacciActivity

}