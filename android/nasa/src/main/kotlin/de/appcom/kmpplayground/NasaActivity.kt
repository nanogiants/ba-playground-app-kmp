package de.appcom.kmpplayground

import NasaPresenter
import NasaPresenterImpl
import NasaView
import PictureOfTheDay
import WebDataSourceImpl
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import de.appcom.kmpplayground.nasa.R
import kotlinx.android.synthetic.main.activity_nasa.*

class NasaActivity : AppCompatActivity(), NasaView {

    var presenter: NasaPresenter =
        NasaPresenterImpl( WebDataSourceImpl(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa)
        setUpToolbar()
//        lifecycle.addObserver(presenter)
    }

    override fun onResume() {
        super.onResume()
        presenter.initializeView()
    }

    fun setUpToolbar() {
        setSupportActionBar(nasa_toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showPictureOfTheDay(pictureOfTheDay: PictureOfTheDay) {
        nasa_title_textview.text = pictureOfTheDay.title
        nasa_explanation_textview.text = pictureOfTheDay.explanation
        Glide.with(this).load(pictureOfTheDay.url).into(nasa_imageview)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setIsLoading(isLoading: Boolean) {
        nasa_loading_progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}