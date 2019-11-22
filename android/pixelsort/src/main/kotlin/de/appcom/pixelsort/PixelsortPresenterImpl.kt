package de.appcom.pixelsort

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.OnLifecycleEvent
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by appcom interactive GmbH on 2019-11-22.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class PixelsortPresenterImpl : PixelsortPresenter {

  lateinit var view: PixelsortView
  lateinit var context: Context
  lateinit var activity: Activity

  private var imagePath: String = ""
  val PERMISSION_CAMERA_REQUEST_ID = 0

  @OnLifecycleEvent(ON_DESTROY)
  fun onDestroy() {
    deleteTempImageFile()
  }

  override fun takePicture() {
    if (hasCameraPermission()) {
      val temporaryFile = createTempImageFile()
      if (temporaryFile != null) {
        val photoURI: Uri =
          FileProvider.getUriForFile(context, "de.appcom.kmpplayground.provider", temporaryFile)
        imagePath = temporaryFile.absolutePath
        view.startCamera(photoURI)
      } else {
        view.showError("File could not be created")
      }
    } else {
      requestCameraPermission()
    }
  }

  override fun choosePictureFromGallery() {
    view.startGallery()
  }

  fun hasCameraPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
      activity,
      Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
  }

  fun requestCameraPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
      view.showError("You need to enable camera in settings")
    } else {
      ActivityCompat.requestPermissions(
        activity,
        arrayOf(Manifest.permission.CAMERA),
        PERMISSION_CAMERA_REQUEST_ID
      )
    }
  }

  override fun onPictureTaken() {
    view.showImage(imagePath)
  }

  override fun onPictureChosen(uri: Uri?) {
    if (uri == null) {
      view.showError("Image not found")
    } else {
      writeImageToFile(uri) // TODO: auslagern auf eigenen thread?
      view.showImage(imagePath)
    }
  }

  override fun onRequestPermissionResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    if (requestCode == PERMISSION_CAMERA_REQUEST_ID && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      // camera permission granted
      takePicture()
    }
  }

  private fun createTempImageFile(): File? {
    // Create an image file name
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    // Access storage directory
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return try {
      File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
      )
    } catch (exception: IOException) {
      null
    }
  }

  private fun writeImageToFile(contentUri: Uri): String {
    val file: File? = createTempImageFile()
    if (file != null) {
      imagePath = file.absolutePath
      var inputStream: InputStream? = null
      var outputStream: OutputStream? = null
      try {
        inputStream = context.getContentResolver().openInputStream(contentUri)
        outputStream = FileOutputStream(file)
        if (inputStream != null) {
          outputStream.write(inputStream.readBytes())
        }
      } catch (exception: Exception) {
        print(exception)
      } finally {
        outputStream?.close()
        inputStream?.close()
      }
    }
    return ""
  }

  fun deleteTempImageFile() {
    if (imagePath != "") {
      File(imagePath).delete()
    }
  }
}