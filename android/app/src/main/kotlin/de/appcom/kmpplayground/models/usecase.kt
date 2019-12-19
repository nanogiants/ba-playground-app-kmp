package de.appcom.kmpplayground.models

import app.domain.UseCase
import app.domain.UseCase.Identifier.FIBONACCI
import app.domain.UseCase.Identifier.NASA
import app.domain.UseCase.Identifier.NOTES
import app.domain.UseCase.Identifier.PIXELSORT
import app.domain.UseCase.Identifier.SETTINGS
import de.appcom.kmpplayground.R

/**
 * Created by appcom interactive GmbH on 2019-12-19.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
val appUseCases = listOf(
  UseCase(
    NASA,
    R.string.nasa_title,
    R.string.nasa_description
  ),
  UseCase(
    SETTINGS,
    R.string.settings_title,
    R.string.settings_description
  ),
  UseCase(
    NOTES,
    R.string.notes_title,
    R.string.notes_description
  ),
  UseCase(
    FIBONACCI,
    R.string.fibonacci_title,
    R.string.fibonacci_description
  ),
  UseCase(
    PIXELSORT,
    R.string.pixelsort_title,
    R.string.pixelsort_description
  )
)