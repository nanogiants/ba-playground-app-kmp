//
//  FibonacciView.swift
//  Playground
//
//  Created by Fabian Heck on 12.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import SwiftUI
import SharedPlayground

struct FibonacciContentView: View {
    
    @State private var inputString: String = ""
    @State private var resultString: String = "?"
    @State private var log1: ThreadLog = ThreadLog(title:"",content:"",time:"")
    @State private var log2: ThreadLog = ThreadLog(title:"",content:"",time:"")
    @State private var log3: ThreadLog = ThreadLog(title:"",content:"",time:"")
    
    private var workHelper: WorkHelper = WorkHelper()
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack() {
                Spacer()
                Text("fibonacci (")
                TextField("n", text: $inputString, onEditingChanged: { changed in
                    if let inputInt = Int(self.inputString) {
                        self.runFibonacci(n: 30)
                    } else {
                        self.log1 = ThreadLog(title:"",content:"",time:"")
                        self.log2 = ThreadLog(title:"",content:"",time:"")
                        self.log3 = ThreadLog(title:"",content:"",time:"")
                    }
                })
                    .frame(width: CGFloat(60.0), height: nil)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .multilineTextAlignment(.center)
                Text(") = \(resultString)")
                Spacer()
                
            }
            Group {
                ThreadLogRow(threadLog: log1)
                ThreadLogRow(threadLog: log2)
                ThreadLogRow(threadLog: log3)
            }
            Spacer()
        }.onAppear(perform: {})
            .padding(20)
    }
    
    func runFibonacci(n: Int) {
        
        let param = n
        let task: (Any)-> Any = { input in
            Fibonacci().calculate(n: input as! Int32)  as Any
        }
        
        // 1
        let start1 = DispatchTime.now()
        let a: Any = workHelper.runOnCallerThread(task: task, param: param)
        resultString = "\(a as! Int)"
        let end1 = DispatchTime.now()
        let nanoTime1 = (end1.uptimeNanoseconds - start1.uptimeNanoseconds)/1000/1000
        log1 = ThreadLog(title: "Thread", content: "",time: "\(nanoTime1) ms")
        // 2
        let start2 = DispatchTime.now()
        workHelper.runOnBackgroundThread(task: task, param: param, onResult: { result in
            let end2 = DispatchTime.now()
            let nanoTime2 = (end2.uptimeNanoseconds - start2.uptimeNanoseconds)/1000/1000
            print(result as! Int)
            self.log2 = ThreadLog(title: "Thread and Worker", content: "",time: "\(nanoTime2) ms")
        })
        
        // 3
        let start3 = DispatchTime.now()
        workHelper.runWithCoroutinesOnUiDispatcher(task: task, param: param, onResult: { result in
            let end3 = DispatchTime.now()
            let nanoTime3 = (end3.uptimeNanoseconds - start3.uptimeNanoseconds)/1000/1000
            print(result as! Int)
            self.log3 = ThreadLog(title: "Thread", content: "",time: "\(nanoTime3) ms")
        })
    }
}

struct ThreadLogRow: View {
    
    let threadLog: ThreadLog
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(threadLog.title).bold()
            Text(threadLog.content)
            Text(threadLog.time)
        }.padding(.top, 20)
    }
    
}

struct FibonacciContentView_Previews: PreviewProvider {
    static var previews: some View {
        FibonacciContentView()
    }
}

struct ThreadLog {
    var title: String
    var content: String
    var time: String
}
