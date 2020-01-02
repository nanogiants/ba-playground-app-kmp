package de.appcom.kmpplayground.notes.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.appcom.kmpplayground.notes.presentation.AddNoteActivity
import de.appcom.kmpplayground.notes.presentation.NotesActivity

@Module
abstract class NotesBindingModule {

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [NotesModule::class])
  abstract fun injectNotesActivity(): NotesActivity

  //  @ActivityScope
  @ContributesAndroidInjector(modules = [AddNoteModule::class])
  abstract fun injectAddNoteActivity(): AddNoteActivity

}