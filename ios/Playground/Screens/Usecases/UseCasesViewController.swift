//
//  UseCasesViewController.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class UseCasesViewController: UIViewController {
    
    weak var coordinator: UseCasesTabCoordinator?
    
    let sectionInsets = UIEdgeInsets(top: 10.0,
                                     left: 20.0,
                                     bottom: 10.0,
                                     right: 20.0)
    
    @IBOutlet weak var useCasesCollectionView: UICollectionView!
    
    var items: [UseCase] = [
        UseCase(id: .Nasa, title: NSLocalizedString("nasa_title", comment:""), description: NSLocalizedString("nasa_description", comment:""), imageName:""),
        UseCase(id: .Settings, title: NSLocalizedString("settings_title", comment:""), description: NSLocalizedString("settings_description", comment:""), imageName:""),
        UseCase(id: .Notes, title: NSLocalizedString("notes_title", comment:""), description: NSLocalizedString("notes_description", comment:""), imageName:""),
        UseCase(id: .Fibonacci, title: NSLocalizedString("fibonacci_title", comment:""), description: NSLocalizedString("fibonacci_description", comment:""), imageName:""),
        UseCase(id: .Pixelsort,title: NSLocalizedString("pixelsort_title", comment:""), description: NSLocalizedString("pixelsort_description", comment:""), imageName: "")
    ]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = NSLocalizedString("usecases_title", comment: "")
        useCasesCollectionView.delegate = self
        useCasesCollectionView.dataSource = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.tabBarController?.tabBar.isHidden = false
        coordinator?.navigationController.navigationBar.prefersLargeTitles = true
    }
}
