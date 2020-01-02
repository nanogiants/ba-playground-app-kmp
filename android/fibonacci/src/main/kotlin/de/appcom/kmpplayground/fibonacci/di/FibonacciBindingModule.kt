package de.appcom.kmpplayground.fibonacci.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.fibonacci.presentation.FibonacciActivity

@Module
abstract class FibonacciBindingModule {

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [FibonacciModule::class])
  abstract fun injectMainActivity(): FibonacciActivity

}