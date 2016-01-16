package com.czeczotka.conversion.running

import org.specs2.mutable.Specification
import com.czeczotka.conversion.running.Distance._

class DistanceSpec extends Specification {

  "Distance.at(pace)" should {
    "create an Activity for a given Pace at a given Distance" in {
      Distance(450).at(Pace(4, 50)) mustEqual Activity(Pace(4, 50), Distance(450))
      marathon at "4:50" mustEqual Activity(Pace(4, 50), Distance(42195))
    }
  }
}
