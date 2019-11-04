//
//  AppTabBarController.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class AppTabBarController: UITabBarController {
    
    let tab0TabCoordinator = UseCasesTabCoordinator(navigationController: UINavigationController())
    let tab1TabCoordinator = ComparisonTabCoordinator(navigationController: UINavigationController())
    let tab2TabCoordinator = AboutTabCoordinator(navigationController: UINavigationController())
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tab0TabCoordinator.start()
        tab1TabCoordinator.start()
        tab2TabCoordinator.start()
        
        viewControllers = [tab0TabCoordinator.navigationController, tab1TabCoordinator.navigationController, tab2TabCoordinator.navigationController]
              hidesBottomBarWhenPushed = false
    }
}
