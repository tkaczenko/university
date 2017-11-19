package io.github.tkaczenko.system_programming

import java.io.{File, PrintWriter}

import scala.io.Source

object Lab2 extends App {
  override def main(args: Array[String]): Unit = {
    val phoneRegex = "^(?:(?:\\+|00)(\\d{1,3})[\\s-]?)?(\\d{9,10})$".r
    val fileName = "test.txt"
    mockFile(fileName)
    Source.fromFile(fileName).getLines()
      .foreach(str => phoneRegex.findFirstIn(str) match {
        case Some(s) => println("Correct phone " + str)
        case None => println("Incrorrect phone " + str)
      })
  }

  def mockFile(name: String): Unit = {
    val writer = new PrintWriter(new File(name))
    writer.write("+380-682633976\n")
    writer.write("+380682633976\n")
    writer.write("0682633976\n")
    writer.write("0682633976\n")
    writer.write("0682633976\n")
    writer.write("044 3934120\n")
    writer.write("1-8884521505\n")
    writer.close()
  }
}
