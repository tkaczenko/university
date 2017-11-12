package io.github.tkaczenko.system_programming

import scala.io.StdIn

object Lab2 extends App {
  val phoneRegex = "^(?:(?:\\+|00)(\\d{1,3})[\\s-]?)?(\\d{9,10})$".r

  println("Pls, write phone number: ")
  val str: String = StdIn.readLine()
  phoneRegex.findFirstIn(str) match {
    case Some(s) => println("Correct phone")
    case None => println("Incrorrect phone")
  }
}
