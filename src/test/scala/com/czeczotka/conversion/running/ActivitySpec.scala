package com.czeczotka.conversion.running

import com.czeczotka.conversion.running.Distance._
import org.specs2.mutable.Specification

class ActivitySpec extends Specification {

  "Activity.totalTime" should {
    "calculate the total time of the activity" in {
      Activity(Pace(5, 0), Distance(5000)).totalTime mustEqual "25m00s"
      Activity("5:00", k1).totalTime mustEqual "5m00s"
      Activity("5:15", metres400).totalTime mustEqual "2m06s"
      Activity("5:15", k10).totalTime mustEqual "52m30s"
      Activity("4:30", metres800).totalTime mustEqual "3m36s"
      Activity("4:30", kilometres(0)).totalTime mustEqual "0m00s"
      Activity("4:30", miles(3)).totalTime mustEqual "21m43s"
    }
  }
}