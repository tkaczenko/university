package io.github.tkaczenko.decision_theory

case class Criteria(minS: Int = 100,
               maxS: Int = 250,
               hS: Int = 10,
               minX: Int = 10,
               maxX: Int = 25,
               hX: Int = 1) {
  private var _nSize = (maxX - minX) / hX + 1
  private var _mSize = (maxS - minS) / hS + 1

  def laplas(matrix: Array[Array[Double]]): Array[Double] = {
    var w = new Array[Double](_nSize)
    for (i <- 0 until _nSize) {
      var sum: Double = 0
      for (j <- 0 until _mSize) {
        sum += matrix(i)(j)
      }
      w(i) = sum / ((maxX - minX) / hX)
    }
    w
  }

  def vald(matrix: Array[Array[Double]]): Array[Double] = {
    var w = new Array[Double](_nSize)
    for (i <- 0 until _nSize) {
      var min = matrix(i)(0)
      for (j <- 1 until _mSize) {
        if (min > matrix(i)(j)) min = matrix(i)(j)
      }
      w(i) = min
    }
    w
  }

  def gurvica(matrix: Array[Array[Double]], a: Double): Array[Double] = {
    var w = new Array[Double](_nSize)
    for (i <- 0 until _nSize) {
      var min = matrix(i)(0)
      var max = matrix(i)(0)
      for (j <- 1 until _mSize) {
        if (min > matrix(i)(j)) min = matrix(i)(j)
        if (max < matrix(i)(j)) max = matrix(i)(j)
      }
      w(i) = a * max + (1 - a) * min
    }
    w
  }

  def sevidg(matrix: Array[Array[Double]]): Array[Array[Double]] = {
    var w = Array.ofDim[Double](_nSize, _mSize)
    val maxs = new Array[Double]((maxS - minS) / hS + 1)
    for (j <- 0 until _mSize) {
      maxs(j) = matrix(0)(j)
      for (i <- 0 until _nSize) {
        if (maxs(j) < matrix(i)(j)) maxs(j) = matrix(i)(j)
      }
    }

    for (i <- 0 until _nSize) {
      for (j <- 0 until _mSize) {
        w(i)(j) = matrix(i)(j) - maxs(j)
      }
    }
    w
  }

  def nSize: Int = _nSize

  def mSize: Int = _mSize
}
