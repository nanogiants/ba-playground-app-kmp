//
//  PixelsortViewController.swift
//  Playground
//
//  Created by Fabian Heck on 22.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class PixelsortViewController: UIViewController {
    
    weak var coordinator: UseCasesTabCoordinator?
    
    @IBOutlet weak var startCameraButton: UIButton!
    @IBOutlet weak var startGalleryButton: UIButton!
    @IBOutlet weak var explanationLabel: UILabel!
    
    @IBOutlet weak var resultImageView: UIImageView!
    
    @IBAction func onStartCameraClicked(_ sender: UIButton) {
        camera()
    }
    
    @IBAction func onStartGalleryClicked(_ sender: UIButton) {
        gallery()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        resultImageView.isHidden = true
        self.title = NSLocalizedString("pixelsort_title", comment: "")
        explanationLabel.text = NSLocalizedString("pixelsort_explanation", comment: "")
        startCameraButton.titleLabel?.text = NSLocalizedString("pixelsort_camera", comment: "")
        startGalleryButton.titleLabel?.text = NSLocalizedString("pixelsort_album", comment: "")
    }
    
    func camera() {
        if UIImagePickerController.isSourceTypeAvailable(.camera){
            let imagePickerController = UIImagePickerController()
            imagePickerController.delegate = self
            imagePickerController.sourceType = .camera
            imagePickerController.mediaTypes = ["public.image"]
            coordinator?.navigationController.present(imagePickerController, animated: true, completion: nil)
        }
    }
    
    func gallery(){
        if UIImagePickerController.isSourceTypeAvailable(.photoLibrary) {
            let imagePickerController = UIImagePickerController()
            imagePickerController.delegate = self
            imagePickerController.sourceType = .photoLibrary
            coordinator?.navigationController.present(imagePickerController, animated: true, completion: nil)
        }
    }
    
}
