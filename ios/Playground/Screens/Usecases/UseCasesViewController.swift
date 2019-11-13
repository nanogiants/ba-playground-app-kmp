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
        UseCase(id: .Pixelsort,title: "Create a dream image", description: "Camera, FileSystem", imageName: ""),
        UseCase(id: .Nasa, title: "Information about the Earth", description: "REST-Api, Data handling", imageName: ""),
        UseCase(id: .Notes ,title: "Take Notes", description: "Database", imageName: ""),
        UseCase(id: .Settings ,title: "Settings", description: "Preferences storage", imageName: ""),
        UseCase(id: .Fibonacci ,title: "Fibonacci", description: "Threads, Generics", imageName: "")
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
