//
//  AboutViewController+TableView.swift
//  Playground
//
//  Created by Fabian Heck on 19.12.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

extension AboutViewController: UITableViewDelegate {
    
}

extension AboutViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: FaqTableViewCell.identifier, for: indexPath) as! FaqTableViewCell
        
        let faqEntry = faqEntries[indexPath.row]
        cell.questionLabel.text = faqEntry.question
        cell.answerLabel.text = faqEntry.answer
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return faqEntries.count
    }
    
}

