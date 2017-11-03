package com.czeczotka.conversion.running

import java.time.LocalTime

import scala.collection.immutable.SortedMap

case class RacePlanner(activity: Activity, timeOfDay: LocalTime) {

  def splits(): Map[Double, (String, String)] = splits(Distance.k1)

  def splits(d: Distance): Map[Double, (String, String)] = SortedMap((for {
      i <- Range.Double.inclusive(d.kilometres, activity.distance.kilometres, d.kilometres) :+ activity.distance.kilometres
    } yield {
      val time = Activity(activity.pace, Distance((i * 1000).toInt)).time
      i -> (time.toString, timeOfDay.plusSeconds(time.toSeconds).toString)
    }): _*)
}