package com.czeczotka.conversion.running

import com.czeczotka.conversion.running.Distance._
import org.specs2.mutable.Specification

import scala.collection.immutable.SortedMap

class ActivitySpec extends Specification {

  "Activity.time" should {
    "calculate the time of the activity" in {
      Activity(Pace(5, 0), Distance(5000)).time  mustEqual Time(0, 25, 0)
      Activity("5:00", k1).time                  mustEqual Time(0, 5, 0)
      Activity("5:15", metres400).time           mustEqual Time(0, 2, 6)
      Activity("5:15", k10).time                 mustEqual Time(0, 52, 30)
      Activity("4:30", metres800).time           mustEqual Time(0, 3, 36)
      Activity("4:30", kilometres(0)).time       mustEqual Time(0, 0, 0)
      Activity("4:30", miles(3)).time            mustEqual Time(0, 21, 43)
      Activity("5:20", halfMarathon).time        mustEqual Time(1, 52, 31)
      Activity("5:25", marathon).time            mustEqual Time(3, 48, 33)
    }
  }

  "Activity.splitsDouble" should {
    "create splits for every kilometer" in {
      Activity("5:00", metres600).splits mustEqual Map()
      Activity("6:00", metres400).splits(Distance(100)) mustEqual SortedMap(0.1 -> "36s",  0.2 -> "1m12s", 0.3 -> "1m48s", 0.4 -> "2m24s")
      Activity("4:45", k5).splits  mustEqual SortedMap(1.0 -> "4m45s",  2.0 -> "9m30s",  3.0 -> "14m15s", 4.0 -> "19m00s", 5.0 -> "23m45s")
      Activity("5:00", k10).splits mustEqual SortedMap(1.0 -> "5m00s",  2.0 -> "10m00s", 3.0 -> "15m00s", 4.0 -> "20m00s", 5.0  -> "25m00s",
                                                       6.0 -> "30m00s", 7.0 -> "35m00s", 8.0 -> "40m00s", 9.0 -> "45m00s", 10.0 -> "50m00s")
      Activity("5:00", Distance(9500)).splits mustEqual SortedMap(1.0 -> "5m00s",  2.0 -> "10m00s", 3.0 -> "15m00s", 4.0 -> "20m00s", 5.0 -> "25m00s",
                                                                  6.0 -> "30m00s", 7.0 -> "35m00s", 8.0 -> "40m00s", 9.0 -> "45m00s")
      Activity("5:40", marathon).splits(k5) mustEqual SortedMap(5.0  -> "28m20s",   10.0 -> "56m40s",   15.0 -> "1h25m00s", 20.0 -> "1h53m20s",
                                                                25.0 -> "2h21m40s", 30.0 -> "2h50m00s", 35.0 -> "3h18m20s", 40.0 -> "3h46m40s")
      marathon in "2h15m25s" splits k10 mustEqual SortedMap(10.0 -> "32m00s", 20.0 -> "1h04m00s", 30.0 -> "1h36m00s", 40.0 -> "2h08m00s")
    }
  }
}