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
    
    var faqEntries: [FaqEntry] = [
        FaqEntry(question: NSLocalizedString("about_faq_q1", comment:""), answer: NSLocalizedString("about_faq_a1", comment:"")),
        FaqEntry(question: NSLocalizedString("about_faq_q2", comment:""), answer: NSLocalizedString("about_faq_a2", comment:"")),
        FaqEntry(question: NSLocalizedString("about_faq_q3", comment:""), answer: NSLocalizedString("about_faq_a3", comment:"")),
        FaqEntry(question: NSLocalizedString("about_faq_q4", comment:""), answer: NSLocalizedString("about_faq_a4", comment:"")),
        FaqEntry(question: NSLocalizedString("about_faq_q5", comment:""), answer: NSLocalizedString("about_faq_a5", comment:"")),
        FaqEntry(question: NSLocalizedString("about_faq_q6", comment:""), answer: NSLocalizedString("about_faq_a6", comment:""))
    ]
    
    weak var coordinator: AboutTabCoordinator?
    
    @IBOutlet weak var faqTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = NSLocalizedString("about_title", comment: "")
        createFaq()
    }
    
    func createFaq() {
        faqTableView.delegate = self
        faqTableView.dataSource = self
        faqTableView.separatorStyle = UITableViewCell.SeparatorStyle.none
        faqTableView.allowsSelection = false
    }
}
