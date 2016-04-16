package com.czeczotka.conversion.running

import com.czeczotka.conversion.running.Distance.k1

import scala.collection.immutable.SortedMap

case class Activity(pace: Pace, distance: Distance) {

  def splits(d: Distance): Map[Double, String] = SortedMap[Double, String](
    (for (i <- Range.Double.inclusive(d.kilometres, distance.kilometres, d.kilometres))
      yield i -> Activity(pace, Distance((i * 1000).toInt)).time.toString): _*)

  def splits: Map[Double, String] = splits(k1)

  def time: Time = {
    val totalSeconds: Int = (distance.metres / 1000.0 * pace.inSeconds).toInt
    val totalMinutes: Int = totalSeconds / 60
    val totalHours: Int = totalMinutes / 60
    Time(totalHours, totalMinutes % 60, totalSeconds % 60)
  }
}