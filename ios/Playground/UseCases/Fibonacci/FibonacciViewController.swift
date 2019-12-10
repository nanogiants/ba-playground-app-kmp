//
//  FibonacciViewController.swift
//  Playground
//
//  Created by Fabian Heck on 13.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit
import SharedPlayground

class FibonacciViewController : UIViewController {
    
    weak var coordinator: UseCasesTabCoordinator?
    
    @IBOutlet weak var explanationLabel: UILabel!
    @IBOutlet weak var inputTextView: UITextField!
    @IBOutlet weak var errorLabel: UILabel!
    @IBOutlet weak var resultLabel: UILabel!
    @IBOutlet weak var time1Label: UILabel!
    @IBOutlet weak var time2Label: UILabel!
    @IBOutlet weak var time3Label: UILabel!
    
    @IBAction func onEditingChanged(_ sender: UITextField) {
        resetUI()
        if let input = Int(sender.text!) {
            if input > 0 && input <= 35 {
                // is number and in range
                runFibonacci(n: input)
            } else {
                showError(message: NSLocalizedString("fibonacci_error", comment:""))
            }
        } else {
            if !sender.text!.isEmpty {
                showError(message: NSLocalizedString("fibonacci_error", comment:""))
            }
        }
    }
    
    private var workHelper: WorkHelper = WorkHelper()
    
    override func viewDidLoad() {
        self.title = NSLocalizedString("fibonacci_title", comment: "")
        explanationLabel.text = NSLocalizedString("fibonacci_explanation", comment:"")
    }
    
    func showError(message: String) {
        errorLabel.text = message
    }
    
    
    func runFibonacci(n: Int) {
        let task: (Any)-> Any = { input in
            Fibonacci().calculate(n: input as! Int32)  as Any
        }
        
        // 1
        DispatchQueue.global(qos: .userInteractive).async {
            let start1 = DispatchTime.now()
            let result: Any = WorkHelper().runOnCallerThread(task: task, param: n)
            DispatchQueue.main.async {
                self.time1Label.text = self.createLog("Platform Api", start: start1, end: DispatchTime.now())
                self.resultLabel.text = "F(\(n)) = \(result as! Int)"
            }
        }
        
        // 2
        let start2 = DispatchTime.now()
        workHelper.runOnBackgroundThread(task: task, param: n, onResult: { result in
            self.time2Label.text = self.createLog("Kotlin/Native Worker", start: start2, end: DispatchTime.now())
        })

        // 3
        let start3 = DispatchTime.now()
        workHelper.runWithCoroutinesOnUiDispatcher(task: task, param: n, onResult: { result in
            self.time3Label.text  = self.createLog("Coroutines", start: start3, end: DispatchTime.now())
        })
    }
    
    func createLog(_ text: String, start: DispatchTime, end: DispatchTime)-> String {
        return "\(text) \((end.uptimeNanoseconds - start.uptimeNanoseconds)/1000/1000) ms"
    }
    
    func resetUI() {
        resultLabel.text = ""
        time1Label.text = ""
        time2Label.text = ""
        time3Label.text = ""
        errorLabel.text = ""
    }
}

