package de.appcom.kmpplayground.nasa.presentation

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.nasa.R
import de.appcom.kmpplayground.nasa.databinding.ActivityNasaBinding
import nasa.domain.PictureOfTheDay
import nasa.presentation.NasaPresenter
import nasa.presentation.NasaView
import javax.inject.Inject

class NasaActivity : DaggerAppCompatActivity(), NasaView {

  @Inject
  lateinit var presenter: NasaPresenter

  lateinit var binding: ActivityNasaBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityNasaBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setUpToolbar()
  }

  override fun onResume() {
    super.onResume()
    presenter.initializeView()
  }

  fun setUpToolbar() {
    setSupportActionBar(binding.nasaToolbar)
    supportActionBar?.title = getString(R.string.nasa_title)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun showPictureOfTheDay(pictureOfTheDay: PictureOfTheDay) {
    binding.nasaTitleTextview.text = pictureOfTheDay.title
    binding.nasaExplanationTextview.text = pictureOfTheDay.explanation
    if (pictureOfTheDay.hasImage) {
      Glide.with(this)
        .load(pictureOfTheDay.url)
        .into(binding.nasaImageview)
    }
  }

  override fun showError(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG)
      .show()
  }

  override fun showError(exception: Exception) {
    Log.d("Web", exception.message, exception)
  }

  override fun setIsLoading(isLoading: Boolean) {
    binding.nasaLoadingProgressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }
}