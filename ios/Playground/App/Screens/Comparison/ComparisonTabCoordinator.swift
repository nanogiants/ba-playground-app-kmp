//
//  ComparisonTabCoordinator.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class ComparisonTabCoordinator: Coordinator {
    
    let tabTitle = NSLocalizedString("comparison_title", comment:"")
    let tabImage = UIImage(named: "ic_chart")
    let tabTag = 1
    
    var navigationController: UINavigationController
    var childCoordinators: [Coordinator]
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
        self.childCoordinators = []
    }
    
    func start() {
        let viewController = ComparisonViewController.instantiate()
        viewController.tabBarItem = UITabBarItem(title: tabTitle, image: tabImage, tag: tabTag)
        viewController.coordinator = self
        navigationController.show(viewController, sender: self)
        navigationController.navigationBar.prefersLargeTitles = true
    }
}
