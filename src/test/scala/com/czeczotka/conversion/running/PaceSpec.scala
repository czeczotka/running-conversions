package com.czeczotka.conversion.running

import com.czeczotka.conversion.running.Pace.string2pace
import org.specs2.mutable.Specification

class PaceSpec extends Specification {

  "Pace.string2pace" should {

    "convert a String with a colon to a Pace object" in {
      string2pace("3:35")   mustEqual Pace(3, 35)
      string2pace("04:55")  mustEqual Pace(4, 55)
      string2pace("5:5")    mustEqual Pace(5, 5)
      string2pace("5:05")   mustEqual Pace(5, 5)
      string2pace("5:50")   mustEqual Pace(5, 50)
      string2pace("12:0")   mustEqual Pace(12, 0)
      string2pace("59:59")  mustEqual Pace(59, 59)
      string2pace("00:01")  mustEqual Pace(0, 1)
    }

    "not convert an invalid String" in {
      def errorMessage(s: String) = s"Could not convert string '$s' to pace. Please provide the pace in the 'mm:ss' format with values from 00:01 to 59:59."
      string2pace("")      must throwA[IllegalArgumentException](errorMessage(""))
      string2pace("000")   must throwA[IllegalArgumentException](errorMessage("000"))
      string2pace("335")   must throwA[IllegalArgumentException](errorMessage("335"))
      string2pace("abc")   must throwA[IllegalArgumentException](errorMessage("abc"))
      string2pace("   ")   must throwA[IllegalArgumentException](errorMessage("   "))
      string2pace("5:65")  must throwA[IllegalArgumentException](errorMessage("5:65"))
      string2pace("a:b")   must throwA[IllegalArgumentException](errorMessage("a:b"))
      string2pace("00:00") must throwA[IllegalArgumentException](errorMessage("00:00"))
      string2pace("60:00") must throwA[IllegalArgumentException](errorMessage("60:00"))
      string2pace("65:55") must throwA[IllegalArgumentException](errorMessage("65:55"))
    }
  }

  "Pace.toString" should {

    "return a String representing the pace" in {
      Pace(6, 12).toString mustEqual "6:12"
      Pace(5, 50).toString mustEqual "5:50"
    }

    "format single digit seconds with a leading zero" in {
      Pace(5, 5).toString mustEqual "5:05"
    }
  }
}