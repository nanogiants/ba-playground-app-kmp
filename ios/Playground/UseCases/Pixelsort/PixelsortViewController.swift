//
//  PixelsortViewController.swift
//  Playground
//
//  Created by Fabian Heck on 22.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import AVFoundation
import Foundation
import UIKit

class PixelsortViewController: UIViewController {
    
    weak var coordinator: UseCasesTabCoordinator?
    
    @IBOutlet weak var startCameraButton: UIButton!
    @IBOutlet weak var startGalleryButton: UIButton!
    @IBOutlet weak var explanationLabel: UILabel!
    
    @IBOutlet weak var resultImageView: UIImageView!
    
    @IBAction func onStartCameraClicked(_ sender: UIButton) {
        startCameraWithPermission()
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
    
    func startCameraWithPermission() {
        switch AVCaptureDevice.authorizationStatus(for: .video) {
        case .authorized:
            self.camera()
        case .notDetermined:
            AVCaptureDevice.requestAccess(for: .video) { granted in
                if granted {
                    DispatchQueue.main.async {
                        self.camera()
                    }
                }
            }
        case .denied:
            showError()
            return
        case .restricted:
            return
        }
    }
    
    func showError() {
           let alert = UIAlertController(title: "No Permission", message: "You need to allow Camera access",         preferredStyle: .alert)

           alert.addAction(UIAlertAction(title: "Settings", style: .default, handler: { _ in
            UIApplication.shared.open(URL(string: UIApplication.openSettingsURLString)!)
           }))
           alert.addAction(UIAlertAction(title: "ok", style: .default, handler: {(_: UIAlertAction!) in
           }))
           self.present(alert, animated: true, completion: nil)
    }
}
