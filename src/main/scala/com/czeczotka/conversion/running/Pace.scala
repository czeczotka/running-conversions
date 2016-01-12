package com.czeczotka.running.conversion

import scala.util.{Success, Failure, Try}


case class Pace(min: Int, sec: Int)


object Pace {

  implicit def string2pace(str: String): Pace = str.split(':') match {
    case Array(m, s) =>
      Try {
        (m.toInt, s.toInt)
      } match {
        case Failure(ex) => exception(str)
        case Success((min, sec)) =>
          if (min >= 0 && min < 60 && sec >= 0 && sec < 60 && (min > 0 || sec > 0)) Pace(min.toInt, sec.toInt)
          else exception(str)
      }
    case _ => exception(str)
  }

  private def exception(s: String) =
    throw new IllegalArgumentException(s"Could not convert string '$s' to pace. Please provide pace in the 'mm:ss' format with values from 00:01 to 59:59.")

}