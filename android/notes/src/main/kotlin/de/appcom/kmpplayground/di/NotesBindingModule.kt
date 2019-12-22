package de.appcom.kmpplayground.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.presentation.AddNoteActivity
import de.appcom.kmpplayground.presentation.NotesActivity

@Module
abstract class NotesBindingModule {

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [NotesModule::class])
  abstract fun injectNotesActivity(): NotesActivity

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [AddNoteModule::class])
  abstract fun injectAddNoteActivity(): AddNoteActivity

}