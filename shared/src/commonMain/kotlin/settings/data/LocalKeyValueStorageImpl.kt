package settings.data

import com.russhwolf.settings.Settings

class LocalKeyValueStorageImpl(val settings: Settings) : LocalKeyValueStorage {

  private val textKey: String = "TEXT_KEY"

  override fun writeText(text: String) = settings.putString(textKey, text)

  override fun readText(): String = settings.getString(textKey, "")

}
