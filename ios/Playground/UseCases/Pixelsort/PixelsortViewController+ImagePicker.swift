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
        picker.dismiss(animated: true, completion: nil)
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        if let image = info[.originalImage] as? UIImage {
            startCameraButton.isHidden = true
            startGalleryButton.isHidden = true
            explanationLabel.isHidden = true
            resultImageView.isHidden = false
            resultImageView.image = image
        } else {
            print("error in imagepickercontroller")
        }
        picker.dismiss(animated: true, completion: nil)
    }
}
