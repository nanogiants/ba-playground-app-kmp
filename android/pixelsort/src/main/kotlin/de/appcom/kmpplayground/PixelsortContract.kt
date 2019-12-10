package de.appcom.kmpplayground

import android.net.Uri
import androidx.lifecycle.LifecycleObserver

/**
 * Created by appcom interactive GmbH on 2019-11-22.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
interface PixelsortPresenter : LifecycleObserver {
  fun takePicture()
  fun choosePictureFromGallery()
  fun onPictureTaken()
  fun onPictureChosen(uri: Uri?)
  fun onRequestPermissionResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  )
}

interface PixelsortView {
  fun startCamera(photoURI: Uri)
  fun startGallery()
  fun showImage(pathToImage: String)
  fun showError(message: String)
}
