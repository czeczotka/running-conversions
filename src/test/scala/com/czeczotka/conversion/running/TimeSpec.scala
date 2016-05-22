package com.czeczotka.conversion.running

import org.specs2.mutable.Specification
import com.czeczotka.conversion.running.Time.{string2time, time}

class TimeSpec extends Specification {

  "Time.toString" should {
    "return a String representing the Time" in {
      Time(1, 2, 3).toString      mustEqual "1h02m03s"
      Time(12, 34, 56).toString   mustEqual "12h34m56s"
      Time(0, 2, 3).toString      mustEqual "2m03s"
      Time(0, 0, 3).toString      mustEqual "3s"
    }
  }

  "Time.string2time" should {
    "convert a String to a Time object" in {
      string2time("5m")         mustEqual Time(0, 5, 0)
      string2time("5m5s")       mustEqual Time(0, 5, 5)
      string2time("05m05s")     mustEqual Time(0, 5, 5)
      string2time("50m50s")     mustEqual Time(0, 50, 50)
      string2time("2h59m")      mustEqual Time(2, 59, 0)
      string2time("15h5m50s")   mustEqual Time(15, 5, 50)
      string2time("0h0m1s")     mustEqual Time(0, 0, 1)
    }

    "not convert an invalid String" in {
      def errorMessage(s: String) = s"Could not convert string '$s' to time. Please provide the time in the 'XXhXXmXXs' format."
      string2time("")              must throwA[IllegalArgumentException](errorMessage(""))
      string2time("hms")           must throwA[IllegalArgumentException](errorMessage("hms"))
      string2time("h11m22s")       must throwA[IllegalArgumentException](errorMessage("h11m22s"))
      string2time("111h222m333s")  must throwA[IllegalArgumentException](errorMessage("111h222m333s"))
      string2time("hms")           must throwA[IllegalArgumentException](errorMessage("hms"))
    }
  }

  "Time.toSeconds" should {
    "return number of seconds" in {
      Time(0, 0, 1).toSeconds mustEqual 1
      Time(0, 1, 1).toSeconds mustEqual 61
      Time(1, 1, 1).toSeconds mustEqual 3661
      Time(3, 51, 59).toSeconds mustEqual 13919
    }
  }

  "Time.time" should {
    "create Time" in {
      time(11, 12) mustEqual Time(0, 11, 12)
      time(11, 12, 13) mustEqual Time(11, 12, 13)
    }
  }
}