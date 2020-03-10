package de.appcom.kmpplayground.settings.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.core.widget.doAfterTextChanged
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.settings.R
import de.appcom.kmpplayground.settings.databinding.ActivitySettingsBinding
import settings.data.LocalKeyValueStorage
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var localKeyValueStorage: LocalKeyValueStorage

  var lastText: String = ""
  private lateinit var binding: ActivitySettingsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySettingsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setUpToolbar()
  }

  override fun onResume() {
    super.onResume()

    lastText = localKeyValueStorage.readText()
    binding.settingsTextinputedittext.setText(lastText)
    binding.settingsTextinputedittext.doAfterTextChanged {
      if (!it.toString().equals(lastText)) {
        localKeyValueStorage.writeText(binding.settingsTextinputedittext.text.toString())
      }
    }
  }

  fun setUpToolbar() {
    setSupportActionBar(binding.settingsToolbar)
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