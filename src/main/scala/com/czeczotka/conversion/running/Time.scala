package com.czeczotka.conversion.running

case class Time(hours: Int = 0, minutes: Int, seconds: Int) {

  override def toString = hours match {
    case 0 => minutes match {
      case 0 => "%ds".format(seconds)
      case _ => "%dm%02ds".format(minutes, seconds)
    }
    case _ => "%dh%02dm%02ds".format(hours, minutes, seconds)
  }
}