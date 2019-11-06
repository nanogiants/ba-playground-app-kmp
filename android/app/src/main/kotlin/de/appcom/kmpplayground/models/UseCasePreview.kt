package de.appcom.kmpplayground.models

/**
 * Created by Fabian Heck on 2019-10-24.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
data class UseCasePreview(
  val id: Int,
  val title: String,
//  val color: Color,
  val description: String = ""
)