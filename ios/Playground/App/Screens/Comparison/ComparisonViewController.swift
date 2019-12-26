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
    
    let averageValue: Float = 0.2
    let useCaseNames: [String] = [
        NSLocalizedString("nasa_title", comment: ""),
        NSLocalizedString("fibonacci_title", comment: ""),
        NSLocalizedString("notes_title", comment: ""),
        NSLocalizedString("pixelsort_title", comment: ""),
        NSLocalizedString("settings_title", comment: ""),
        NSLocalizedString("game_title", comment: "")
    ]
    let useCaseValues: [Float] = [0.1, 0.2, 0.3, 0.4, 0.4, 0.5]
    
    @IBOutlet weak var progressDataViewAll: ProgressDataView!
    @IBOutlet weak var progressDataViewUc1: ProgressDataView!
    @IBOutlet weak var progressDataViewUc2: ProgressDataView!
    @IBOutlet weak var progressDataViewUc3: ProgressDataView!
    @IBOutlet weak var progressDataViewUc4: ProgressDataView!
    @IBOutlet weak var progressDataViewUc5: ProgressDataView!
    @IBOutlet weak var progressDataViewUc6: ProgressDataView!
    @IBOutlet weak var averageTitleLabel: UILabel!
    @IBOutlet weak var usecasesTitleLabel: UILabel!
    @IBOutlet weak var explanationLabel: UILabel!
  
    weak var coordinator: ComparisonTabCoordinator?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = NSLocalizedString("comparison_title", comment: "")
        
        explanationLabel.text = NSLocalizedString("compare_explanation", comment: "")
        averageTitleLabel.text = NSLocalizedString("compare_title_average", comment: "")
        usecasesTitleLabel.text = NSLocalizedString("compare_title_usecases", comment: "")
        
        progressDataViewAll.titleLabel.text = NSLocalizedString("compare_average", comment: "")
        progressDataViewAll.progress = averageValue
        
        progressDataViewUc1.titleLabel.text = useCaseNames[0]
        progressDataViewUc1.progress = useCaseValues[0]
        
        progressDataViewUc2.titleLabel.text = useCaseNames[1]
        progressDataViewUc2.progress = useCaseValues[1]
        
        progressDataViewUc3.titleLabel.text = useCaseNames[2]
        progressDataViewUc3.progress = useCaseValues[2]
        
        progressDataViewUc4.titleLabel.text = useCaseNames[3]
        progressDataViewUc4.progress = useCaseValues[3]
        
        progressDataViewUc5.titleLabel.text = useCaseNames[4]
        progressDataViewUc5.progress = useCaseValues[4]
        
        progressDataViewUc6.titleLabel.text = useCaseNames[5]
        progressDataViewUc6.progress = useCaseValues[5]
    }
}
