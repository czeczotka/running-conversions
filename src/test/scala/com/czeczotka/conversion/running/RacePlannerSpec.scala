package com.czeczotka.conversion.running

import java.time.LocalTime

import org.specs2.mutable.Specification
import com.czeczotka.conversion.running.Distance._
import com.czeczotka.conversion.running.Time.string2time

import scala.collection.immutable.SortedMap

class RacePlannerSpec extends Specification {

  "RacePlanner" should {
    "plan splits with time" in {
      RacePlanner(k10 in "1h00m", LocalTime.of(9,0)).splits mustEqual
        SortedMap(1.0 -> ("6m00s", "09:06"),  2.0  -> ("12m00s", "09:12"), 3.0 -> ("18m00s", "09:18"), 4.0 -> ("24m00s", "09:24"),
                  5.0 -> ("30m00s", "09:30"), 6.0  -> ("36m00s", "09:36"), 7.0 -> ("42m00s", "09:42"), 8.0 -> ("48m00s", "09:48"),
                  9.0 -> ("54m00s", "09:54"), 10.0 -> ("1h00m00s", "10:00"))
      RacePlanner(k10 in "1h00m", LocalTime.of(9,0)).splits(Distance(2000)) mustEqual
        SortedMap(2.0 -> ("12m00s", "09:12"), 4.0  -> ("24m00s", "09:24"), 6.0 -> ("36m00s", "09:36"),
                  8.0 -> ("48m00s", "09:48"), 10.0 -> ("1h00m00s", "10:00"))
      RacePlanner(k10 in "1h00m", LocalTime.of(9,0)).splits(k5) mustEqual
        SortedMap(5.0 -> ("30m00s", "09:30"), 10.0 -> ("1h00m00s", "10:00"))
      RacePlanner(marathon in "3h52m", LocalTime.of(10,1)).splits(k5) mustEqual
        SortedMap(
          5.0  -> ("27m25s",   "10:28:25"), 10.0 -> ("54m50s",   "10:55:50"), 15.0 -> ("1h22m15s", "11:23:15"),
          20.0 -> ("1h49m40s", "11:50:40"), 25.0 -> ("2h17m05s", "12:18:05"), 30.0 -> ("2h44m30s", "12:45:30"),
          35.0 -> ("3h11m55s", "13:12:55"), 40.0 -> ("3h39m20s", "13:40:20"))
    }
  }
}