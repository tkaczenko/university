/**
  * Created by tkaczenko on 25.04.17.
  */
object Lab1 extends App {
  def pow(x: Double, n: Int): Double = {
    def _power(res: Double, n: Int): Double = n match {
      case 0 => res
      case _ => _power(res * x, n - 1)
    }

    _power(1, n)
  }

  def powMinusOne(n: Int): Double = {
    if (n % 2 == 1) -1.0
    else 1.0
  }

  def otn(x: Double, n: Int): Double = {
    powMinusOne(n) * ((x: Double, k: Int) => pow(x, k) / k) (x, 2 * n + 1)
  }

  def sum(x: Double, n: Int, eps: Double): Double = {
    val elN = otn(x, n)
    if (math.abs(elN) < eps) elN
    else if (math.abs(1 / elN) < eps) elN
    else elN + sum(x, n + 1, eps)
  }

  def arctg(x: Double) = sum(x, 0, 1e-3)

  override def main(args: Array[String]): Unit = {
    val x = 2
    println(arctg(x))
  }
}
