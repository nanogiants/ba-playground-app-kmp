//
//  SettingsViewController.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import SwiftUI
import SharedPlayground

struct ContentView: View {
    
    @State private var message: String = ""
    
    private var localKeyValueStorage = SettingsProvider().instantiate()
    
    var body: some View {
        Form {
            Section(header: Text("Settings"), footer: Text(NSLocalizedString("settings_explanation", comment:""))) {
                TextField(NSLocalizedString("settings_placeholder", comment:""), text: $message, onEditingChanged: { changed in
                    if changed == false {
                        self.localKeyValueStorage.writeText(text: self.message)
                    }
                }, onCommit: {
                    self.localKeyValueStorage.writeText(text: self.message)
                })
            }
        }.onAppear(perform: getLastMessage)
            .navigationBarTitle(NSLocalizedString("settings_title", comment: ""))
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
