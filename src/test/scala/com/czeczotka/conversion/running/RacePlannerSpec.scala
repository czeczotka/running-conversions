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
          35.0 -> ("3h11m55s", "13:12:55"), 40.0 -> ("3h39m20s", "13:40:20"), 42.195 -> ("3h51m22s", "13:52:22"))
      RacePlanner(halfMarathon in "1h40m", LocalTime.of(8,20)).splits(k5) mustEqual
        SortedMap(
          5.0  -> ("23m40s",   "08:43:40"), 10.0 -> ("47m20s", "09:07:20"), 15.0 -> ("1h11m00s", "09:31"),
          20.0 -> ("1h34m40s", "09:54:40"), 21.097 -> ("1h39m51s", "09:59:51"))
    }

    "pretty print race details" in {
      RacePlanner(k5 in "25m", LocalTime.of(9,0)).prettyPrint mustEqual
        """*----------------------------------*
          #|  split | race time | time of day |
          #|----------------------------------|
          #|   1.0  |    5m00s  |    09:05    |
          #|   2.0  |   10m00s  |    09:10    |
          #|   3.0  |   15m00s  |    09:15    |
          #|   4.0  |   20m00s  |    09:20    |
          #|   5.0  |   25m00s  |    09:25    |
          #*----------------------------------*""".stripMargin('#')
    }
  }
}