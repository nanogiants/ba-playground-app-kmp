//
//  SettingsViewController.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import SwiftUI

struct ContentView: View {
    
    @State private var message: String = ""
    
//    private var userDefaultsService = UserDefaultsService()
    
    var body: some View {
        Form {
            Section(header: Text("Text persistent Speichern"), footer: Text("Hier steht eine ausführliche Anleitung, was man alles machen kann")) {
                TextField("Enter a message", text: $message, onCommit: {
//                    self.userDefaultsService.writeMessage(message: self.message)
                })
            }
        }.onAppear(perform: getLastMessage)
    }
    
    func getLastMessage() {
//        message = userDefaultsService.readMessage()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
