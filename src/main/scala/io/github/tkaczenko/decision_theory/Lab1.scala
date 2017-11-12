package io.github.tkaczenko.decision_theory

object Lab1 extends App {
  private def f(s: Int, x: Int): Double =
    s * 1000 - x * 15000 - 20000 - (s / 10 - x) * 2000 * 365

  override def main(args: Array[String]): Unit = {
    val criteria = Criteria()
    val nSize = criteria.nSize
    val mSize = criteria.mSize
    val minS = criteria.minS
    val maxS = criteria.maxS
    val hS = criteria.hS
    val minX = criteria.minX
    val maxX = criteria.maxX
    val hX = criteria.hX

    var matrix = Array.ofDim[Double](nSize, mSize)
    println("\tМатрица полезности:")
    println(" X\t")
    for (j <- 0 until mSize) {
      print((minS + j * hS) + "\t")
    }
    println()
    for (j <- 0 until mSize) {
      print("---------")
    }
    println()
    for (i <- 0 until nSize) {
      print(" " + (minX + i * hX) + " |\t")
      for (j <- 0 until mSize) {
        matrix(i)(j) = f(minS + j * hS, minX + i * hX)
        print((matrix(i)(j) / 1000) + "к\t")
      }
      println()
    }
    println("\n\tКритерий Лапласа:")
    val lap = criteria.laplas(matrix).toList
    printArray(lap)
    var max = lap.max
    println(s"Максимум $max достигается при X = ${minX + hX * lap.indexOf(max)}.\n")

    println("\n\tКритерий Вальда:")
    val vald = criteria.vald(matrix).toList
    printArray(vald)
    max = vald.max
    println(s"Максимальный возможный убыток не превысит ${-max} достигается при X = ${minX + hX * vald.indexOf(max)}.\n")

    println("\n\tКритерий Гурвица:")
    val gurvicaPesimims = criteria.gurvica(matrix, 0.1).toList
    printArray(gurvicaPesimims)
    max = gurvicaPesimims.max
    println(s"При пессимистическом настроении: Максимум $max достигается при X = ${minX + hX * gurvicaPesimims.indexOf(max)}.\n")

    println("\n\tКритерий Гурвица:")
    val gurvicaFifty = criteria.gurvica(matrix, 0.5).toList
    printArray(gurvicaFifty)
    max = gurvicaFifty.max
    println(s"При равных шансах: Максимум $max достигается при X = ${minX + hX * gurvicaFifty.indexOf(max)}.\n")

    println("\n\tКритерий Гурвица:")
    val gurvicaOptimistic = criteria.gurvica(matrix, 0.9).toList
    printArray(gurvicaOptimistic)
    max = gurvicaOptimistic.max
    println(s"В состоянии полного оптимизма: Максимум $max достигается при X = ${minX + hX * gurvicaOptimistic.indexOf(max)}.\n")

    println("\n\tКритерий Сэджвика:")
    val sevidg = criteria.sevidg(matrix)
    println("\tМатрица сожалений:")
    println(" X\t")
    for (j <- 0 until mSize) {
      print((minS + j * hS) + "\t")
    }
    println()
    for (j <- 0 until mSize) {
      print("---------")
    }

    for (i <- 0 until nSize) {
      print(" " + (minX + i * hX) + " |\t")
      for (j <- 0 until mSize) {
        matrix(i)(j) = f(minS + j * hS, minX + i * hX)
        print((sevidg(i)(j) / 1000) + "к\t")
      }
      println()
    }
    println("\n\tКритерий Вальда:")
    val valdSevidg = criteria.vald(sevidg).toList
    printArray(valdSevidg)
    max = valdSevidg.max
    println(s"Максимальный возможный убыток не превысит ${-max} достигается при X = ${minX + hX * valdSevidg.indexOf(max)}.\n")
  }

  private def printArray(array: List[Double]): Unit = {
    for (i <- array.indices) {
      println(s"w[$i] = ${array(i) / 1000}к")
    }
  }
}
