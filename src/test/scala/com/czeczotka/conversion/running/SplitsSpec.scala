package com.czeczotka.conversion.running

import org.specs2.mutable.Specification

class SplitsSpec extends Specification {

  "Splits" should {
    "contain correct values" in {
      Splits(5.0, 1.0).markers mustEqual List(1.0, 2.0, 3.0, 4.0, 5.0)
      Splits(4.5, 1.0).markers mustEqual List(1.0, 2.0, 3.0, 4.0, 4.5)
      Splits(0.9, 1.0).markers mustEqual List(0.9)
      Splits(1.1, 1.0).markers mustEqual List(1.0, 1.1)
      Splits(0.5, 0.1).markers mustEqual List(0.1, 0.2, 0.3, 0.4, 0.5)
      Splits(2.5, 0.4).markers mustEqual List(0.4, 0.8, 1.2, 1.6, 2.0, 2.4, 2.5)
    }
  }
}