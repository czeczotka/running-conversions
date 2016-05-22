package com.czeczotka.conversion.running

import Distance._

object RunningConversions extends App {

  println("Welcome to running-conversions!")
  println()
  println("Calculate 5k splits to run the marathon in 3h45m:")
  println(marathon in "3h45m" splits k5 foreach println)
  println()
  println("Calculate the pace for 1h30m half marathon:")
  println(halfMarathon in "1h30m" pace)

}