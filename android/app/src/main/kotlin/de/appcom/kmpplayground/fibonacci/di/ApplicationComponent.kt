package de.appcom.kmpplayground.fibonacci.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import de.appcom.kmpplayground.PlaygroundApplication
import de.appcom.kmpplayground.nasa.di.NasaBindingModule
import de.appcom.kmpplayground.notes.di.NotesBindingModule
import de.appcom.kmpplayground.pixelsort.di.PixelsortBindingModule
import de.appcom.kmpplayground.settings.di.SettingsBindingModule
import javax.inject.Singleton

/**
 * Created by Fabian Heck on 2019-10-24.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */

@Component(
  modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class,
    FibonacciBindingModule::class,
    NasaBindingModule::class,
    NotesBindingModule::class,
    PixelsortBindingModule::class,
    SettingsBindingModule::class,
    GameBindingsModule::class
  ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<PlaygroundApplication> {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance applicationContext: Context): ApplicationComponent
  }

}