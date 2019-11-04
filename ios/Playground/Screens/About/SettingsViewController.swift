//
//  SettingsViewController.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSettings

struct ContentView: View {
    
    @State private var message: String = "" {
        didSet {
            print("didset")
             self.localKeyValueStorage.writeText(text: self.message)
        }
    }
    
    private var localKeyValueStorage = LocalKeyValueStorageImpl(settings: AppleSettings(delegate: UserDefaults.standard))
    
    var body: some View {
        Form {
            Section(header: Text("Text persistent Speichern"), footer: Text("Hier steht eine ausführliche Anleitung, was man alles machen kann")) {
                TextField("Enter a message", text: $message, onEditingChanged: { changed in
                    if changed == false {
                        self.localKeyValueStorage.writeText(text: self.message)
                    }
                }, onCommit: {
                    self.localKeyValueStorage.writeText(text: self.message)
                })
            }
        }.onAppear(perform: getLastMessage)
    }
    
    func getLastMessage() {
        message = localKeyValueStorage.readText()
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
