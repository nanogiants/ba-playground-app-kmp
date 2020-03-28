package settings

import settings.data.LocalKeyValueStorage
import com.russhwolf.settings.AppleSettings
import platform.Foundation.NSUserDefaults
import settings.data.LocalKeyValueStorageImpl

class SettingsProvider {

  fun instantiate(): LocalKeyValueStorage {
    return LocalKeyValueStorageImpl(AppleSettings(delegate = NSUserDefaults.standardUserDefaults()))
  }

}