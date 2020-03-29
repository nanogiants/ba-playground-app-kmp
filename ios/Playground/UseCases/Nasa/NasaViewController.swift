//
//  NasaViewController.swift
//  Playground
//
//  Created by Fabian Heck on 05.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit
import shared

class NasaViewController: UIViewController, NasaView {
    
    weak var coordinator: UseCasesTabCoordinator?
    
    @IBOutlet weak var nasaImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var contentLabel: UILabel!
    @IBOutlet weak var spinner: UIActivityIndicatorView!
    @IBOutlet weak var imageSpinner: UIActivityIndicatorView!
    
    override func viewDidLoad() {
        self.title = NSLocalizedString("nasa_title", comment: "")
        let presenter: NasaPresenter = NasaPresenterImpl(webDataSource: WebDataSourceImpl(), view: self )
        presenter.initializeView()
        imageSpinner.isHidden = true
    }
    
    func showError(exception: KotlinException) {
       //
    }
    
    func showPictureOfTheDay(pictureOfTheDay: PictureOfTheDay) {
        self.titleLabel.text = pictureOfTheDay.title
        setAttributedContentLabel(text: pictureOfTheDay.explanation)
        if pictureOfTheDay.hasImage {
            load(pathToImage: URL(string: pictureOfTheDay.url))
        }
    }
    
    func setIsLoading(isLoading: Bool) {
        spinner.isHidden = !isLoading
    }
    
    func showError(message: String) {
        print("\(message)")
    }
    
    func load(pathToImage: URL?) {
        if let url = pathToImage {
            imageSpinner.isHidden = false
            DispatchQueue.global().async { [weak self] in
                if let data = try? Data(contentsOf: url) {
                    if let image = UIImage(data: data) {
                        DispatchQueue.main.async {
                            self?.imageSpinner.isHidden = true
                            self?.nasaImageView.image = image
                        }
                    }
                }
            }
        }
    }
    
    func setAttributedContentLabel(text: String) {
        let attributedString = NSMutableAttributedString(string: text)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.lineSpacing = 5
        attributedString.addAttribute(NSAttributedString.Key.paragraphStyle, value:paragraphStyle, range:NSMakeRange(0, attributedString.length))
        self.contentLabel.attributedText = attributedString
    }
}
