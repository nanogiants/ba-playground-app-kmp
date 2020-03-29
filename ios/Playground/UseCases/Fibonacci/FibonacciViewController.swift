//
//  FibonacciViewController.swift
//  Playground
//
//  Created by Fabian Heck on 13.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit
import shared

class FibonacciViewController : UIViewController {
    
    weak var coordinator: UseCasesTabCoordinator?
    private var workHelper: WorkHelper = WorkHelper()
    
    @IBOutlet weak var explanationLabel: UILabel!
    @IBOutlet weak var inputTextView: UITextField!
    @IBOutlet weak var errorLabel: UILabel!
    @IBOutlet weak var resultLabel: UILabel!
    @IBOutlet weak var time1Label: UILabel!
    @IBOutlet weak var time2Label: UILabel!
    
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
    
    override func viewDidLoad() {
        self.title = NSLocalizedString("fibonacci_title", comment: "")
        explanationLabel.text = NSLocalizedString("fibonacci_explanation", comment:"")
    }
    
    func showError(message: String) {
        errorLabel.text = message
    }
    
    func runFibonacci(n: Int) {
        let task: (Any)-> Any = { input in
            let inputCast: Int = input as! Int
            let castedN: Int32 = Int32(inputCast)
            return Fibonacci().calculate(n: castedN) as Any
        }
        
        DispatchQueue.global(qos: .userInteractive).async {
            let timer1 = Timer()
            timer1.start()
            let result: Any = WorkHelper().runOnCallerThread(task: task, param: n)
            timer1.stop()
            let endTime = timer1.endTime
            DispatchQueue.main.async {
                self.time1Label.text = "Platform Api \(endTime) ms"
                self.resultLabel.text = "F(\(n)) = \(result as! Int)"
            }
        }
        
        let timer2 = Timer()
        timer2.start()
        workHelper.runWithCoroutinesOnUiDispatcher(task: task, param: n, onResult: { result in
            timer2.stop()
            self.time2Label.text  = "Coroutines \(timer2.endTime) ms"
        })
    }
    
    func resetUI() {
        resultLabel.text = ""
        time1Label.text = ""
        time2Label.text = ""
        errorLabel.text = ""
    }
}

