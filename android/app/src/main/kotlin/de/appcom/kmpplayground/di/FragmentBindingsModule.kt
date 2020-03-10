package de.appcom.kmpplayground.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.annotations.FragmentScope
import de.appcom.kmpplayground.fragments.about.AboutFragment
import de.appcom.kmpplayground.fragments.about.AboutModule
import de.appcom.kmpplayground.fragments.comparison.ComparisonFragment
import de.appcom.kmpplayground.fragments.comparison.ComparisonModule
import de.appcom.kmpplayground.fragments.usecases.UseCasesFragment
import de.appcom.kmpplayground.fragments.usecases.UseCasesModule

@Module
abstract class FragmentBindingsModule {

  @FragmentScope
  @ContributesAndroidInjector(modules = [AboutModule::class])
  abstract fun injectAboutFragment(): AboutFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [ComparisonModule::class])
  abstract fun injectComparisonFragment(): ComparisonFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [UseCasesModule::class])
  abstract fun injectUseCasesFragment(): UseCasesFragment
}