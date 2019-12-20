//
//  AboutTabCoordinator.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class AboutTabCoordinator: Coordinator {
    
    let tabTitle = NSLocalizedString("about_title", comment:"")
    let tabImage = UIImage(named: "ic_info")
    let tabTag = 2
    
    var navigationController: UINavigationController
    var childCoordinators: [Coordinator]

    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
        self.childCoordinators = []
    }

    func start() {
        let viewController = AboutViewController.instantiate()
        // setup tabbar item
        viewController.tabBarItem = UITabBarItem(title: tabTitle, image: tabImage, tag: tabTag)
        // setup viewmodel
        // setup Services
        viewController.coordinator = self
        navigationController.show(viewController, sender: self)
        navigationController.navigationBar.prefersLargeTitles = true
    }
}
