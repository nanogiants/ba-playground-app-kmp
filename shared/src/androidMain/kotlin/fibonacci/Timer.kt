package fibonacci

import android.os.SystemClock

/**
 * Created by appcom interactive GmbH on 2019-11-22.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
actual class Timer {
  actual var startTime: Long = -1
  actual var endTime: Long = -1
  actual fun currentTime(): Long = SystemClock.elapsedRealtime()
}