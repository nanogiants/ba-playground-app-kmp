//
//  NotesViewModel.swift
//  Playground
//
//  Created by Fabian Heck on 10.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import SwiftUI
import SharedPlayground

class NotesViewModel: ObservableObject, NotesView, AddNoteView {
    
    @Published
    private(set) var notes: [Note] = []
    
    var view: NotesContentView? = nil
    private var notesPresenter: NotesPresenter? = nil
    private var addNotePresenter: AddNotePresenter? = nil
    
    init() {
        let notesDataBase: NotesDatabase = NotesDatabaseHelper().createDatabase()
        let notesDataSource: NotesDataSource = NotesDataSourceImpl(notesDatabase: notesDataBase)
        self.notesPresenter = NotesPresenterImpl(view: self, notesDataSource: notesDataSource)
        self.addNotePresenter = AddNotePresenterImpl(view: self, notesDataSource: notesDataSource)
    }
    
    func setView(_ view: NotesContentView) {
        self.view = view
        self.view.environmentObject(self)
    }
    
    func fetch() {
        notesPresenter?.loadNotes()
    }
    
    func addNote(title: String, content: String) {
        addNotePresenter?.addNote(note: Note(id: getNowInMillis(), title: title, content:content, createdAt: getNowInMillis()))
    }
    
    func getNowInMillis() -> Int64 {
        return Int64((Date().timeIntervalSince1970 * 1000.0).rounded())
    }
    
    func removeNote(index: Int) {
        notesPresenter?.deleteNote(note: notes[index])
    }
    
    func showNotes(notes: [Note]) {
        self.notes = notes
    }
    
    func onNoteDeleted() {
              fetch()
        print("onDeleteNote")
    }
    
    func onNoteAdded() {
        fetch()
//        view?.closeModal()
        print("onAddNote")
    }
    
    func showError(message: String) {
        print(message)
    }
}
