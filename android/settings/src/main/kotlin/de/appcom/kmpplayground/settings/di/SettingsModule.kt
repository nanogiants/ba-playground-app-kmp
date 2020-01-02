package de.appcom.kmpplayground.settings.di

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import dagger.Module
import dagger.Provides
import de.appcom.kmpplayground.settings.presentation.SettingsActivity
import settings.data.LocalKeyValueStorage
import settings.data.LocalKeyValueStorageImpl

@Module
object SettingsModule {

  @Provides
  fun provideLocalKeyValueStorage(localKeyValueStorageImpl: LocalKeyValueStorageImpl): LocalKeyValueStorage {
    return localKeyValueStorageImpl
  }

  @Provides
  fun provideLocalKeyValueStorageImpl(settings: Settings): LocalKeyValueStorageImpl {
    return LocalKeyValueStorageImpl(settings)
  }

  @Provides
  fun provideSettings(activity: SettingsActivity): Settings {
    return AndroidSettings(activity.getPreferences(Context.MODE_PRIVATE))
  }

}