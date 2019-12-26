//
//  UseCase.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

struct UseCase {
    
    var id: Identifier
    var title: String = ""
    var description: String = ""
    var imageName: String = ""
    var color: UIColor
    
    enum Identifier {
        case Pixelsort
        case Nasa
        case Notes
        case Locating
        case Settings
        case Fibonacci
        case Game
    }
}
