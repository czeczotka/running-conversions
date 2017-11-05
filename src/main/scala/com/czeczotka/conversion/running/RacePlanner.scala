package com.czeczotka.conversion.running

import java.time.LocalTime

import scala.collection.immutable.SortedMap

case class RacePlanner(activity: Activity, timeOfDay: LocalTime) {

  private lazy val header =
    """*----------------------------------*
      #|  split | race time | time of day |
      #|----------------------------------|""".stripMargin('#')

  private lazy val footer = "*----------------------------------*"

  private lazy val body = {
    val s = splits()
    val pattern = s"%${s.values.map(_._1).map(_.length).max}s"
    s.map { s =>
      s"|   ${s._1}  |   ${pattern.format(s._2._1)}  |    ${s._2._2}    |"
    }.mkString("\n")
  }

  lazy val prettyPrint: String = Seq(header, body, footer).mkString("\n")

  def splits(): Map[Double, (String, String)] = splits(Distance.k1)

  def splits(d: Distance): Map[Double, (String, String)] = SortedMap((for {
      i <- Range.Double.inclusive(d.kilometres, activity.distance.kilometres, d.kilometres) :+ activity.distance.kilometres
    } yield {
      val time = Activity(activity.pace, Distance((i * 1000).toInt)).time
      i -> (time.toString, timeOfDay.plusSeconds(time.toSeconds).toString)
    }): _*)
}