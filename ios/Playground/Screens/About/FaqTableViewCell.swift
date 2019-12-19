//
//  FaqTableViewCell.swift
//  Playground
//
//  Created by Fabian Heck on 19.12.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class FaqTableViewCell: UITableViewCell {
    
    static let identifier = "FaqTableViewCell"
    
    @IBOutlet weak var questionLabel: UILabel!
    @IBOutlet weak var answerLabel: UILabel!
    
}
