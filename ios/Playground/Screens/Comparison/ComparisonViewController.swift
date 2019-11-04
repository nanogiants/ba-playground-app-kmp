//
//  ComparisonViewController.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class ComparisonViewController : UIViewController {
    
    weak var coordinator: ComparisonTabCoordinator?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = NSLocalizedString("comparison_title", comment: "")
    }
}
