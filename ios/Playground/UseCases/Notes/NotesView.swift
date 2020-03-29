//
//  NotesView.swift
//  Playground
//
//  Created by Fabian Heck on 10.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct NotesContentView: View {
    
    @State private var modalIsShown = false
    @ObservedObject var viewModel: NotesViewModel
    
    var body: some View {
        HStack {
            List {
                ForEach(viewModel.notes, id: \.self) { note in
                    NotesRow(note: note)
                }.onDelete(perform: removeNote)
            }
            Spacer()
        }.navigationBarItems(trailing:
            Button("Add", action: {
                print("bar is clicked")
                self.modalIsShown = true})
        )
        .navigationBarTitle(NSLocalizedString("notes_title", comment: ""))
        .sheet(isPresented: $modalIsShown, onDismiss: { }) {
            AddNoteModal(
                onModalCancel: { self.closeModal() },
                onModalDone: { title, content in
                    self.viewModel.addNote(title: title, content: content)
                    self.closeModal()
            }
            )
        }.onAppear(perform: viewModel.fetch)
    }
    
    func closeModal() {
        // use this workaround because the onDismiss callback is not working
        UIApplication.shared.windows[0].rootViewController?.dismiss(animated: true, completion: { })
    }
    
    private func removeNote(at offsets: IndexSet) {
        for index in offsets {
            viewModel.removeNote(index: index)
        }
    }
}

struct NotesRow: View {
    var note: Note
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("\(note.title)")
                .bold()
            Text("\(note.content)")
        }
    }
}

struct AddNoteModal: View {
    
    var onModalCancel: () -> Void
    var onModalDone: (String, String) -> Void
    
    @State private var titleString: String = ""
    @State private var contentString: String = ""
    
    var body: some View {
        NavigationView {
            VStack {
                TextField("Title", text: $titleString)
                    .padding(.bottom, 10)
                TextField("Content", text: $contentString)
                Spacer()
            }
            .padding(20)
            .navigationBarTitle(Text("Add Note"), displayMode: .inline)
            .navigationBarItems(
                leading:
                Button(action: {
                    self.onModalCancel()
                }) {
                    Text("Cancel")
                },
                trailing:
                Button(action: {
                    self.onModalDone(self.titleString, self.contentString)
                }){
                    Text("Done")
                }
            )
        }
    }
}

struct TodoContentView_Previews: PreviewProvider {
    static var previews: some View {
        NotesContentView(viewModel: NotesViewModel())
    }
}
