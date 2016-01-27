package com.czeczotka.conversion.running

import org.specs2.mutable.Specification

class TimeSpec extends Specification {

  "Time.toString" should {
    "return a String representing the Time" in {
      Time(1, 2, 3).toString mustEqual "1h02m03s"
      Time(12, 34, 56).toString mustEqual "12h34m56s"
      Time(0, 2, 3).toString mustEqual "2m03s"
      Time(0, 0, 3).toString mustEqual "3s"
    }
  }
}
