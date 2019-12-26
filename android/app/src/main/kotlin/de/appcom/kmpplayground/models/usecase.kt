package de.appcom.kmpplayground.models

import app.domain.UseCase
import app.domain.UseCase.Identifier.FIBONACCI
import app.domain.UseCase.Identifier.GAME
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
    "#FFAF4C",
    R.string.nasa_description,
    R.drawable.uc_cloud
  ),
  UseCase(
    SETTINGS,
    R.string.settings_title,
    "#ED765A",
    R.string.settings_description,
    R.drawable.uc_storage
  ),
  UseCase(
    NOTES,
    R.string.notes_title,
    "#A9D1E6",
    R.string.notes_description,
    R.drawable.uc_storage
  ),
  UseCase(
    FIBONACCI,
    R.string.fibonacci_title,
    "#201C3D",
    R.string.fibonacci_description,
    R.drawable.uc_callsplit
  ),
  UseCase(
    PIXELSORT,
    R.string.pixelsort_title,
    "#C39265",
    R.string.pixelsort_description,
    R.drawable.uc_camera
  ),
  UseCase(
    GAME,
    R.string.game_title,
    "#d18d45",
    R.string.game_description,
    R.drawable.uc_game
  )
)