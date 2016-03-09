package com.czeczotka.conversion.running

import org.specs2.mutable.Specification

class SplitsSpec extends Specification {

  "Splits" should {
    "contain correct values" in {
      Splits(distance = 5.0, step = 1.0).markers mustEqual List(1.0, 2.0, 3.0, 4.0, 5.0)
      Splits(distance = 4.5, step = 1.0).markers mustEqual List(1.0, 2.0, 3.0, 4.0, 4.5)
      Splits(distance = 0.9, step = 1.0).markers mustEqual List(0.9)
      Splits(distance = 1.1, step = 1.0).markers mustEqual List(1.0, 1.1)
      Splits(distance = 0.5, step = 0.1).markers mustEqual List(0.1, 0.2, 0.3, 0.4, 0.5)
      Splits(distance = 2.5, step = 0.4).markers mustEqual List(0.4, 0.8, 1.2, 1.6, 2.0, 2.4, 2.5)
    }
  }
}