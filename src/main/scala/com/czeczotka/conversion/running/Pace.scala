package com.czeczotka.running.conversion


case class Pace(min: Int, sec: Int)


object Pace {

  implicit def string2pace(str: String): Pace = {
    str.indexOf(':') match {
      case colon if colon < 1 => throw new IllegalArgumentException(s"Could not convert string '$str' to pace")
      case colon =>
        val secString = str.substring(colon + 1, str.length)
        val sec = secString.length match {
          case 0 => 0
          case _ => secString.toInt
        }
        val min = str.substring(0, colon).toInt
        Pace(min, sec)
    }
  }
}