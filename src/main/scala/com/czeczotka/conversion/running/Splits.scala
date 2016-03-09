package com.czeczotka.conversion.running

case class Splits(distance: Double, step: Double) {

  val _distance = BigDecimal(distance)
  val _step = BigDecimal(step)

  def markers: List[BigDecimal] = (Range.BigDecimal(_step, _distance, _step).toSet + _distance).toList.sorted

}