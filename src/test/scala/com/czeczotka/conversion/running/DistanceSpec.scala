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

  "Distance.in(time)" should {
    "create a corresponding activity" in {
      metres400 in "1m42s"         mustEqual Activity(Pace(4, 15), metres400)
      k10 in "49m59s"              mustEqual Activity(Pace(4, 59), k10)
      k10 in "50m00s"              mustEqual Activity(Pace(5, 0), k10)
      k10 in "50m09s"              mustEqual Activity(Pace(5, 0), k10)
      k10 in "50m10s"              mustEqual Activity(Pace(5, 1), k10)
      halfMarathon in "1h47m12s"   mustEqual Activity(Pace(5, 4), halfMarathon)
      halfMarathon in "1h47m15s"   mustEqual Activity(Pace(5, 5), halfMarathon)
      halfMarathon in "2h15m22s"   mustEqual Activity(Pace(6, 24), halfMarathon)
      halfMarathon in "2h15m23s"   mustEqual Activity(Pace(6, 25), halfMarathon)
      marathon in "4h00m"          mustEqual Activity(Pace(5, 41), marathon)
      (marathon in "4h00m" pace)   mustEqual Pace(5, 41)
    }
  }

  "Distance.kilometres" should {
    "calculate distance in kilometres" in {
      metres400.kilometres     mustEqual 0.4
      metres1000.kilometres    mustEqual 1.0
      halfMarathon.kilometres  mustEqual 21.097
    }
  }
}