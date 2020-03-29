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
import shared

class UseCasesCoordinator: Coordinator {
    
    //let tabTitle = NSLocalizedString("usecases_title", comment:"")
    //let tabImage = UIImage(named: "ic_category")
    //let tabTag = 0
    
    var navigationController: UINavigationController
    var childCoordinators: [Coordinator]
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
        self.childCoordinators = []
    }
    
    private func navigateToUseCase(viewController: UIViewController) {
        viewController.navigationItem.largeTitleDisplayMode = .never // small title
        navigationController.show(viewController, sender: self)
    }
    
    func start() {
        let viewController = UseCasesViewController.instantiate()
        viewController.coordinator = self
        navigationController.show(viewController, sender: self)
        navigationController.navigationBar.prefersLargeTitles = true
    }
    
    func navigateToPixelsort() {
        let viewController = PixelsortViewController.instantiate(from: "Pixelsort")
        viewController.coordinator = self
        navigateToUseCase(viewController: viewController)
    }
    
    func navigateToSettings() {
        let viewController = UIHostingController(rootView: ContentView())
        navigateToUseCase(viewController: viewController)
    }
    
    func navigateToNasa() {
        let viewController = NasaViewController.instantiate(from: "Nasa")
        viewController.coordinator = self
        navigateToUseCase(viewController: viewController)
    }
    
    func navigateToNotes() {
        let viewModel = NotesViewModel()
        let notesContentView = NotesContentView(viewModel: viewModel)
        viewModel.view = notesContentView
        let viewController = UIHostingController(rootView: notesContentView)
        navigateToUseCase(viewController: viewController)
    }
    
    func navigateToFibonacci() {
        let viewController = FibonacciViewController.instantiate(from: "Fibonacci")
        viewController.coordinator = self
        navigateToUseCase(viewController: viewController)
    }
    
    func navigateToGame() {
        let viewController = GameViewController.instantiate(from: "Game")
        viewController.coordinator = self
        navigateToUseCase(viewController: viewController)
    }
    
    func navigateToAbout() {
        let viewController = AboutViewController.instantiate()
        //viewController.coordinator = self
        navigateToUseCase(viewController: viewController)
    }
}
