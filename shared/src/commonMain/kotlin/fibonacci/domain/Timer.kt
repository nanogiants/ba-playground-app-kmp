package fibonacci.domain

/**
 * Created by appcom interactive GmbH on 2019-11-22.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
expect class Timer {
  var startTime: Long
  var endTime: Long

  fun currentTime(): Long
}

fun Timer.start() {
  startTime = currentTime()
}

fun Timer.stop() {
  if (startTime != -1L) {
    endTime = currentTime() - startTime
  }
}
