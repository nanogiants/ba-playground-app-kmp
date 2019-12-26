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
        UseCase(id: .Nasa, title: NSLocalizedString("nasa_title", comment:""), description: NSLocalizedString("nasa_description", comment:""), imageName:"uc_cloud", color: UIColor(red: 1, green: 0.686, blue: 0.298, alpha: 1)),
        UseCase(id: .Settings, title: NSLocalizedString("settings_title", comment:""), description: NSLocalizedString("settings_description", comment:""), imageName:"uc_storage", color: UIColor(red: 0.929, green: 0.463, blue: 0.353, alpha: 1)),
        UseCase(id: .Notes, title: NSLocalizedString("notes_title", comment:""), description: NSLocalizedString("notes_description", comment:""), imageName:"uc_storage", color: UIColor(red: 0.663, green: 0.82, blue: 0.902, alpha: 1)),
        UseCase(id: .Fibonacci, title: NSLocalizedString("fibonacci_title", comment:""), description: NSLocalizedString("fibonacci_description", comment:""), imageName:"uc_callsplit", color: UIColor(red: 0.125, green: 0.11, blue: 0.239, alpha: 1)),
        UseCase(id: .Pixelsort,title: NSLocalizedString("pixelsort_title", comment:""), description: NSLocalizedString("pixelsort_description", comment:""), imageName: "uc_camera", color: UIColor(red: 0.765, green: 0.573, blue: 0.396, alpha: 1))
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
