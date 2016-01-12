package com.czeczotka.running.conversion

import com.czeczotka.running.conversion.Pace.string2pace
import org.specs2.mutable.Specification

class PaceSpec extends Specification {

  "Pace object" should {

    "convert a String with a colon to a Pace object" in {
      string2pace("3:35")   mustEqual Pace(3, 35)
      string2pace("04:55")  mustEqual Pace(4, 55)
      string2pace("5:5")    mustEqual Pace(5, 5)
      string2pace("5:05")   mustEqual Pace(5, 5)
      string2pace("5:")     mustEqual Pace(5, 0)
      string2pace("5:50")   mustEqual Pace(5, 50)
      string2pace("12:0")   mustEqual Pace(12, 0)
      string2pace("59:59")  mustEqual Pace(59, 59)
    }
  }
}