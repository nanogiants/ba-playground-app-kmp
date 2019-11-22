package fibonacci

/**
 * Created by appcom interactive GmbH on 2019-11-22.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
actual class Timer {
  actual var startTime: Long = -1
  actual var endTime: Long = -1

  // There seems to be swift classes that cannot be accessed from here
  // I could not find a possibility to use DispatchTimer.now()
  // actual fun currentTime(): Long = DispatchTime.now()
  actual fun currentTime(): Long = -1
}