package com.czeczotka.conversion.running

case class Activity(pace: Pace, distance: Distance) {

  def splits: Map[Int, String] = (for (km <- 1 to distance.kilometres.toInt) yield km -> Activity(pace, Distance(km * 1000)).time.toString).toMap

  def time: Time = {
    val totalSeconds: Int = (distance.metres / 1000.0 * pace.inSeconds).toInt
    val totalMinutes: Int = totalSeconds / 60
    val totalHours: Int = totalMinutes / 60
    Time(totalHours, totalMinutes % 60, totalSeconds % 60)
  }
}