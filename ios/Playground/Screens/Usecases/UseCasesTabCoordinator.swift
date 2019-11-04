//
//  UseCasesTabCoordinator.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI

class UseCasesTabCoordinator: Coordinator {
    
    let tabTitle = NSLocalizedString("usecases_title", comment:"")
    let tabImage = UIImage(named: "ic_info")
    let tabTag = 0
    
    var navigationController: UINavigationController
    var childCoordinators: [Coordinator]
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
        self.childCoordinators = []
    }
    
    func start() {
        let viewController = UseCasesViewController.instantiate()
        // setup viewmodel
        // setup Services
        viewController.tabBarItem = UITabBarItem(title: tabTitle, image: tabImage, tag: tabTag)
        viewController.coordinator = self
        navigationController.show(viewController, sender: self)
        navigationController.navigationBar.prefersLargeTitles = true
    }
    
    func navigateToPixelsort() {
//        let viewController = PixelsortViewController.instantiate(from: "Pixelsort")
//        viewController.coordinator = self
//        navigationController.show(viewController, sender: self)
//        navigationController.tabBarController?.tabBar.isHidden = true
//        //        navigationController.tabBarController?.hidesBottomBarWhenPushed = false
//        // childCoordinator -> PixelsortCoordinator
    }
    
    func navigateToSettings() {
        let viewController = UIHostingController(rootView: ContentView())
        navigationController.show(viewController, sender: self)
        navigationController.tabBarController?.tabBar.isHidden = true
    }
    
    func navigateToNasa() {
//        let viewController = NasaViewController.instantiate(from: "Nasa")
//        viewController.tabBarItem = UITabBarItem(title: tabTitle, image: tabImage, tag: tabTag)
//        viewController.coordinator = self
//        navigationController.show(viewController, sender: self)
//        navigationController.navigationBar.prefersLargeTitles = true
//        navigationController.tabBarController?.tabBar.isHidden = true
    }
}
