package com.czeczotka.conversion.running

import com.czeczotka.conversion.running.Distance._
import org.specs2.mutable.Specification

class ActivitySpec extends Specification {

  "Activity.totalTime" should {
    "calculate the total time of the activity" in {
      Activity(Pace(5, 0), Distance(5000)).totalTime  mustEqual "25m00s"
      Activity("5:00", k1).totalTime                  mustEqual "5m00s"
      Activity("5:15", metres400).totalTime           mustEqual "2m06s"
      Activity("5:15", k10).totalTime                 mustEqual "52m30s"
      Activity("4:30", metres800).totalTime           mustEqual "3m36s"
      Activity("4:30", kilometres(0)).totalTime       mustEqual "0m00s"
      Activity("4:30", miles(3)).totalTime            mustEqual "21m43s"
      Activity("5:20", halfMarathon).totalTime        mustEqual "1h52m31s"
      Activity("5:25", marathon).totalTime            mustEqual "3h48m33s"
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