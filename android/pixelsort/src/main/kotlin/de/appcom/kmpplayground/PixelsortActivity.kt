package de.appcom.kmpplayground

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.provider.MediaStore.Images
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pixelsort.pixelsort_album_button
import kotlinx.android.synthetic.main.activity_pixelsort.pixelsort_camera_button
import kotlinx.android.synthetic.main.activity_pixelsort.pixelsort_imageview
import kotlinx.android.synthetic.main.activity_pixelsort.pixelsort_textView
import kotlinx.android.synthetic.main.activity_pixelsort.pixelsort_toolbar

/**
 * Created by appcom interactive GmbH on 2019-11-22.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class PixelsortActivity : AppCompatActivity(),
  PixelsortView {

  lateinit var presenter: PixelsortPresenter

  val REQUEST_IMAGE_CAPTURE = 1
  val REQUEST_IMAGE_GALLERY = 2

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_pixelsort)
    buildDependencies()
    setSupportActionBar(pixelsort_toolbar)
    supportActionBar?.title = getString(R.string.pixelsort_title)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  /**
   * This method needs to be called before any dependency of this class is used.
   */
  private fun buildDependencies() {
    presenter = PixelsortPresenterImpl().also {
      it.view = this
      it.context = this
      it.activity = this
      this.lifecycle.addObserver(it)
    }
  }

  override fun onResume() {
    super.onResume()
    // setup click listener
    pixelsort_camera_button.setOnClickListener { view -> presenter.takePicture() }
    pixelsort_album_button.setOnClickListener { view -> presenter.choosePictureFromGallery() }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun showImage(pathToImage: String) {
    pixelsort_imageview.visibility = View.VISIBLE
    pixelsort_camera_button.visibility = View.GONE
    pixelsort_album_button.visibility = View.GONE
    pixelsort_textView.visibility = View.GONE
    Glide.with(this).load(pathToImage).into(pixelsort_imageview)
  }

  override fun showError(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
  }

  override fun startCamera(photoURI: Uri) {
    // create and configure intent to capture a picture with the camera
    val cameraIntent = Intent(ACTION_IMAGE_CAPTURE).apply {
      putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
    }
    // start intent only if any activity can handle it
    if (cameraIntent.resolveActivity(packageManager) != null) {
      startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
    }
  }

  override fun startGallery() {
    val galleryIntent = Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI)
    galleryIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    startActivityForResult(galleryIntent, REQUEST_IMAGE_GALLERY)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
      // user took a photo
      presenter.onPictureTaken()
    } else if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
      // user chose from gallery
      presenter.onPictureChosen(data?.data)
    } else if (resultCode == Activity.RESULT_CANCELED) {
      // user canceled
    }
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    presenter.onRequestPermissionResult(requestCode, permissions, grantResults)
  }
}