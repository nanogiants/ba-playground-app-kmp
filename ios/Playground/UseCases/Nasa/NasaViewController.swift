//
//  NasaViewController.swift
//  Playground
//
//  Created by Fabian Heck on 05.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit
import SharedNasa

class NasaViewController: UIViewController, NasaView {
    
    weak var coordinator: UseCasesTabCoordinator?
    
    @IBOutlet weak var nasaImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var contentLabel: UILabel!
    
    override func viewDidLoad() {
        var presenter: NasaPresenter = NasaPresenterImpl(webDataSource: WebDataSourceImpl(), view: self )
    }
    
    func showPictureOfTheDay(pictureOfTheDay: PictureOfTheDay) {
        self.titleLabel.text = pictureOfTheDay.title
        self.contentLabel.text = pictureOfTheDay.explanation
        // TODO download and show image
    }
    
    func setIsLoading(isLoading: Bool) {
       //TODO
    }
    
    func showError(message: String) {
        //TOdo
        print("\(message)")
    }
    
}
