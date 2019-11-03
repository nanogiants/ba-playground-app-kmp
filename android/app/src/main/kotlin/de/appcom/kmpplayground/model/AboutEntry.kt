package de.appcom.kmpplayground.model

import androidx.annotation.StringRes

data class AboutEntry(
  @StringRes val titleStringRes: Int,
  @StringRes val descriptionStringRes: Int
)