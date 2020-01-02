package de.appcom.kmpplayground.settings.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.core.widget.doAfterTextChanged
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.settings.R
import kotlinx.android.synthetic.main.activity_settings.settings_textinputedittext
import kotlinx.android.synthetic.main.activity_settings.settings_toolbar
import settings.data.LocalKeyValueStorage
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var localKeyValueStorage: LocalKeyValueStorage

  var lastText: String = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    setUpToolbar()
  }

  override fun onResume() {
    super.onResume()

    lastText = localKeyValueStorage.readText()
    settings_textinputedittext.setText(lastText)
    settings_textinputedittext.doAfterTextChanged {
      if (!it.toString().equals(lastText)) {
        localKeyValueStorage.writeText(settings_textinputedittext.text.toString())
      }
    }
  }

  fun setUpToolbar() {
    setSupportActionBar(settings_toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.title = getString(R.string.settings_title)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) {
      onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }
}