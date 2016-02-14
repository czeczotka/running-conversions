package com.czeczotka.conversion.running

import com.czeczotka.conversion.running.Distance._
import org.specs2.mutable.Specification

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

  "Activity.splits" should {
    "create splits for every kilometer" in {
      Activity("5:00", metres600).splits mustEqual Map()
      Activity("5:00", k10).splits mustEqual Map(1 -> "5m00s",  2 -> "10m00s", 3 -> "15m00s", 4 -> "20m00s", 5 -> "25m00s",
                                                 6 -> "30m00s", 7 -> "35m00s", 8 -> "40m00s", 9 -> "45m00s", 10 -> "50m00s")
      Activity("4:45", k5).splits mustEqual  Map(1 -> "4m45s",  2 -> "9m30s",  3 -> "14m15s", 4 -> "19m00s", 5 -> "23m45s")
      Activity("5:00", Distance(9500)).splits mustEqual Map(1 -> "5m00s",  2 -> "10m00s", 3 -> "15m00s", 4 -> "20m00s", 5 -> "25m00s",
                                                            6 -> "30m00s", 7 -> "35m00s", 8 -> "40m00s", 9 -> "45m00s")
    }
  }
}