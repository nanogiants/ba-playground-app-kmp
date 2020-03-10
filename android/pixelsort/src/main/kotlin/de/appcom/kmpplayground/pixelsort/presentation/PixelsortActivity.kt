package de.appcom.kmpplayground.pixelsort.presentation

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
import com.bumptech.glide.Glide
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.pixelsort.R
import de.appcom.kmpplayground.pixelsort.databinding.ActivityPixelsortBinding
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 2019-11-22.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class PixelsortActivity : DaggerAppCompatActivity(),
  PixelsortView {

  @Inject
  lateinit var presenter: PixelsortPresenter

  private lateinit var binding: ActivityPixelsortBinding

  val REQUEST_IMAGE_CAPTURE = 1
  val REQUEST_IMAGE_GALLERY = 2

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityPixelsortBinding.inflate(layoutInflater)
    setContentView(binding.root)
    lifecycle.addObserver(presenter)
    setSupportActionBar(binding.pixelsortToolbar)
    supportActionBar?.title = getString(R.string.pixelsort_title)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onResume() {
    super.onResume()
    // setup click listener
    binding.pixelsortCameraButton.setOnClickListener { view -> presenter.takePicture() }
    binding.pixelsortAlbumButton.setOnClickListener { view -> presenter.choosePictureFromGallery() }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun showImage(pathToImage: String) {
    binding.pixelsortImageview.visibility = View.VISIBLE
    binding.pixelsortCameraButton.visibility = View.GONE
    binding.pixelsortAlbumButton.visibility = View.GONE
    binding.pixelsortTextView.visibility = View.GONE
    Glide.with(this).load(pathToImage).into(binding.pixelsortImageview)
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