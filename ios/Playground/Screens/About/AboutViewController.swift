//
//  AboutViewController.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class AboutViewController : UIViewController {
    
    weak var coordinator: AboutTabCoordinator?
        
    @IBOutlet var faqTitleLabels: [UILabel]!
    @IBOutlet var faqDescriptionLabels: [UILabel]!
    
    @IBOutlet weak var faqTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = NSLocalizedString("about_title", comment: "")
        createFaq()
    }
    
    func createFaq() {
        faqTitleLabels[0].text = NSLocalizedString("about_faq_usecase_q", comment: "")
        faqDescriptionLabels[0].text = NSLocalizedString("about_faq_usecase_a", comment: "")
        faqTitleLabels[1].text = NSLocalizedString("about_faq_legal_q", comment: "")
        faqDescriptionLabels[1].text = NSLocalizedString("about_faq_legal_a", comment: "")
    }
}
