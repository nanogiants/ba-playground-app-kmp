package fibonacci

import kotlinx.cinterop.LongVarOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.posix.time
import platform.posix.time_t
import platform.posix.time_tVar

/**
 * Created by appcom interactive GmbH on 2019-11-22.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
actual class Timer {
  actual var startTime: Long = -1
  actual var endTime: Long = -1

  // Attempt 1: Swift
  // https://kotlinlang.org/docs/reference/native/objc_interop.html
  // "A swift library can be used in kotlin code if its API is exported to Objective-C with @objc.
  // Pure swift modules are not supported yet"
  // DispatchTimer cannot be accessed here
  // actual fun currentTime(): Long = DispatchTime.now()


  // Attempt 2: C
  // c posix library, standard library for POSIX systems
  // time.h is part of this library
  // this solution is inspired by
  // https://github.com/ktorio/ktor/blob/master/ktor-utils/posix/src/io/ktor/util/date/DateNative.kt
  actual fun currentTime(): Long {
    // allocated memory in this block will be automatically disposed at the end of this scope.
    memScoped {
      // allocate space for time_t object/struct
      val timeHolder: LongVarOf<time_t> = alloc<time_tVar>()

      // time(), defined in time.h
      // time_t time( time_t *second )
      // returns seconds since 00:00:00 UTC, January 1, 1970
      // timeHolder.ptr is not null, the returned value is also stored in the object pointed to
      time(timeHolder.ptr)

      // dereference pointer and change seconds to milliseconds
      val current: Long = timeHolder.value * 1000L
      // return long
      return current
    }
  }

}