package com.czeczotka.conversion.running

case class Distance(metres: Int) {

  def kilometres  = metres / 1000.0

  def at(pace: Pace): Activity = Activity(pace, this)

  def in(time: Time): Activity = {
    val paceInSeconds = time.toSeconds / (metres / 1000.0)
    val pace = Pace((paceInSeconds / 60).toInt, (paceInSeconds % 60).toInt)
    Activity(pace, this)
  }
}

object Distance {

  val metres400 =     Distance(400)
  val metres600 =     Distance(600)
  val metres800 =     Distance(800)
  val metres1000 =    Distance(1000)
  val metres1200 =    Distance(1200)
  val metres1600 =    Distance(1600)
  val metres2000 =    Distance(2000)
  val k1 =            Distance(1000)
  val k5 =            Distance(5000)
  val k10 =           Distance(10000)
  val miles10 =       Distance(16093)
  val halfMarathon =  Distance(21097)
  val marathon =      Distance(42195)

  def metres(m: Int) = Distance(m)
  def km(d: Double) = Distance((d * 1000).toInt)
  def kilometres(d: Double) = km(d)
  def miles(d: Double) = km(d * 1.60934)

}