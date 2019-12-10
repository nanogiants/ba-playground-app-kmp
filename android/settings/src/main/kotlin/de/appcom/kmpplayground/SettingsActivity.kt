package de.appcom.kmpplayground

import LocalKeyValueStorage
import LocalKeyValueStorageImpl
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.russhwolf.settings.AndroidSettings
import kotlinx.android.synthetic.main.activity_settings.settings_textinputedittext
import kotlinx.android.synthetic.main.activity_settings.settings_toolbar

class SettingsActivity : AppCompatActivity() {

  var lastText: String = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    setUpToolbar()
  }

  override fun onResume() {
    super.onResume()
    val localKeyValueStorage: LocalKeyValueStorage =
      LocalKeyValueStorageImpl(AndroidSettings(getPreferences(Context.MODE_PRIVATE)))
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