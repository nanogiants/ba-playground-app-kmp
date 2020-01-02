package de.appcom.kmpplayground.notes.di

import dagger.Module
import dagger.Provides
import de.appcom.kmpplayground.NotesDatabase
import de.appcom.kmpplayground.notes.presentation.AddNoteActivity
import notes.data.NotesDataSource
import notes.data.NotesDataSourceImpl
import notes.data.NotesDatabaseHelper
import notes.presentation.AddNotePresenter
import notes.presentation.AddNotePresenterImpl
import notes.presentation.AddNoteView

@Module
object AddNoteModule {

  @Provides
  fun provideAddNoteView(activity: AddNoteActivity): AddNoteView {
    return activity
  }

  @Provides
  fun provideAddNotePresenterImpl(
    view: AddNoteView,
    notesDataSource: NotesDataSource
  ): AddNotePresenterImpl {
    return AddNotePresenterImpl(view, notesDataSource)
  }

  @Provides
  fun provideAddNotePresenter(addNotePresenterImpl: AddNotePresenterImpl): AddNotePresenter {
    return addNotePresenterImpl
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
  fun provideNotesDatabase(activity: AddNoteActivity): NotesDatabase {
    return NotesDatabaseHelper(activity).createDatabase()
  }

}