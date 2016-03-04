package com.czeczotka.conversion.running

import scala.language.implicitConversions
import scala.util.{Success, Failure, Try}


case class Pace(min: Int, sec: Int) {

  override def toString = "%d:%02d".format(min, sec)

  def inSeconds = 60 * min + sec

}


object Pace {

  val zeroTo59 = 0 to 59

  implicit def string2pace(str: String): Pace = str.split(':') match {
    case Array(m, s) =>
      Try {
        (m.toInt, s.toInt)
      } match {
        case Failure(ex) => exception(str)
        case Success((min, sec)) =>
          if (zeroTo59.contains(min) && zeroTo59.contains(sec) && (min > 0 || sec > 0)) Pace(min, sec)
          else exception(str)
      }
    case _ => exception(str)
  }

  private def exception(s: String) =
    throw new IllegalArgumentException(
      s"Could not convert string '$s' to pace. Please provide the pace in the 'mm:ss' format with values from 00:01 to 59:59.")

}