package de.appcom.kmpplayground.notes.di

import dagger.Module
import dagger.Provides
import de.appcom.kmpplayground.NotesDatabase
import de.appcom.kmpplayground.notes.presentation.NotesActivity
import notes.data.NotesDataSource
import notes.data.NotesDataSourceImpl
import notes.data.NotesDatabaseHelper
import notes.presentation.NotesPresenter
import notes.presentation.NotesPresenterImpl
import notes.presentation.NotesView

@Module
object NotesModule {

  @Provides
  fun provideNotesView(activity: NotesActivity): NotesView {
    return activity
  }

  @Provides
  fun provideNotesPresenterImpl(view: NotesView, notesDataSource: NotesDataSource): NotesPresenterImpl {
    return NotesPresenterImpl(view, notesDataSource)
  }

  @Provides
  fun provideNotesPresenter(notesPresenterImpl: NotesPresenterImpl): NotesPresenter {
    return notesPresenterImpl
  }

  @Provides
  fun provideNotesDataSourceImpl(notesDatabase: NotesDatabase): NotesDataSourceImpl {
    return NotesDataSourceImpl(notesDatabase)
  }

  @Provides
  fun provideNotesDataSource(notesDataSourceImpl: NotesDataSourceImpl): NotesDataSource {
    return notesDataSourceImpl
  }

  @Provides
  fun provideNotesDatabase(activity: NotesActivity): NotesDatabase {
    return NotesDatabaseHelper(activity).createDatabase()
  }

}