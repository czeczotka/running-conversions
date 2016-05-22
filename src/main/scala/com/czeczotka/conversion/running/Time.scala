package com.czeczotka.conversion.running

import scala.language.implicitConversions


case class Time(hours: Int = 0, minutes: Int, seconds: Int) {

  override def toString = hours match {
    case 0 => minutes match {
      case 0 => "%ds".format(seconds)
      case _ => "%dm%02ds".format(minutes, seconds)
    }
    case _ => "%dh%02dm%02ds".format(hours, minutes, seconds)
  }

  def toSeconds = hours * 3600 + minutes * 60 + seconds

}


object Time {

  val min = """(\d{1,2})m""".r
  val minSec = """(\d{1,2})m(\d{1,2})s""".r
  val hourMin = """(\d{1,2})h(\d{1,2})m""".r
  val hourMinSec = """(\d{1,2})h(\d{1,2})m(\d{1,2})s""".r

  implicit def string2time(str: String): Time = str match {
    case min(minutes) => Time(0, minutes.toInt, 0)
    case minSec(minutes, seconds) => Time(0, minutes.toInt, seconds.toInt)
    case hourMin(hours, minutes) => Time(hours.toInt, minutes.toInt, 0)
    case hourMinSec(hours, minutes, seconds) => Time(hours.toInt, minutes.toInt, seconds.toInt)
    case _ => exception(str)
  }

  private def exception(s: String) =
    throw new IllegalArgumentException(s"Could not convert string '$s' to time. Please provide the time in the 'XXhXXmXXs' format.")

  def time(hours: Int, minutes: Int, seconds: Int) = Time(hours, minutes, seconds)
  def time(minutes: Int, seconds: Int) = Time(0, minutes, seconds)

}