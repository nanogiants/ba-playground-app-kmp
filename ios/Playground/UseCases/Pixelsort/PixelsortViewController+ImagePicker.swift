//
//  PixelsortViewController+ImagePicker.swift
//  Playground
//
//  Created by Fabian Heck on 22.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

extension PixelsortViewController  : UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        picker.dismiss(animated: true, completion: nil) // TODO
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        if let image = info[.originalImage] as? UIImage {
            //            self.imagePickedBlock?(image)
            // TODO: add to image view
            startCameraButton.isHidden = true
            startGalleryButton.isHidden = true
            explanationLabel.isHidden = true
            
            resultImageView.isHidden = false
            resultImageView.image = image
            print("hey i may have an image")
        } else {
            // something went wrong
            print("error in imagepickercontroller")
        }
        picker.dismiss(animated: true, completion: nil) // TODO
    }
}
