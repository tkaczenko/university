package io.github.tkaczenko.system_modulating

import io.github.tkaczenko.system_modulating.SignificanceLevel.SignificanceLevel

object SignificanceLevel extends Enumeration {
  type SignificanceLevel = Value
  val SL_0_05, SL_0_025 = Value
}

object ChiSquareCritical {
  private val values_0_05 = Array(3.841, 5.991, 7.815, 9.488, 11.07, 12.592, 14.067, 15.507, 16.919, 18.307, 19.675, 21.026, 22.362, 23.685, 24.996, 26.296, 27.587, 28.869, 30.144, 31.41, 32.671, 33.924, 35.172, 36.415, 37.652, 38.885, 40.113, 41.337, 42.557, 43.773)
  private val values_0_025 = Array(5.0239, 7.3778, 9.3484, 11.143, 12.833, 14.449, 16.013, 17.535, 19.023, 20.483, 21.92, 23.337, 24.736, 26.119, 27.488, 28.845, 30.191, 31.526, 32.852, 34.17, 35.479, 36.781, 38.076, 39.364, 40.646, 41.923, 43.195, 44.461, 45.722, 46.979)

  def GetValue(DoF: Int, sl: SignificanceLevel): Double = {
    sl match {
      case SignificanceLevel.SL_0_05 =>
        return values_0_05(DoF - 1)
      case SignificanceLevel.SL_0_025 =>
        return values_0_025(DoF - 1)
    }
    0
  }

  private val E: Double = 0.1e-5

  private def SimpsonIntegral(func: ((Double) => Double), a: Double, b: Double): Double = {
    ((b - a) / 6) * (func(a) + 4 * func((a + b) / 2) + func(b))
  }

  private def LaplasFunction(x: Double): Double = {
    math.exp(-x * x / 2)
  }

  def GetLaplasFunction(x: Double): Double = {
    val a = 0.0
    val b = x
    var int1: Double = 0
    var int2: Double = 0
    var incr: Double = 0
    var c: Int = 2;
    int2 = SimpsonIntegral(LaplasFunction, a, b);
    do {
      int1 = int2;
      int2 = 0;
      incr = (b - a) / c;
      for (i <- 0 until c) {
        int2 = int2 + SimpsonIntegral(LaplasFunction, a + incr * i, a + incr * (i + 1));
      }
      c += 1;
    }
    while (math.abs(int1 - int2) >= E);
    return int2 / math.sqrt(2 * math.Pi);
  }
}
