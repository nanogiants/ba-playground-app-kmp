//
//  Coordinator.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

protocol Coordinator: class {
    
    var childCoordinators: [Coordinator] {get set}
    
    var navigationController: UINavigationController {get set}
    
    func start()
}
