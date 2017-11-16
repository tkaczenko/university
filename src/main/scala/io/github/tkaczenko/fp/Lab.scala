/**
  * Created by tkaczenko on 24.04.17.
  */
object Lab extends App {
  def pow[T](x: T, n: T)(implicit num: Integral[T]): T = {
    import num.mkNumericOps
    if (n == 0) num.one
    else x * pow(x, n - num.one)
  }

  def powminusone[T](n: T)(implicit i: Integral[T]): T = {
    import i.mkNumericOps
    if (n % i.fromInt(2) == i.one) i.fromInt(-1)
    else i.one
  }

  def otn[T](x: T, n: T)(implicit f: Integral[T]): T = {
    import f.mkNumericOps
    val fun = (x: T, k: T) => pow(x, k) / k
    powminusone(n) * fun(x, f.fromInt(2) * n + f.one)
  }

  def sum[T](x: T, n: T, eps: Double)(implicit num: Integral[T]): T = {
    import num._
    val elN = otn(x, n)
    if (num.abs(elN).toDouble() < eps) elN
    else elN + sum(x, n + num.one, eps)
  }

  def arctg[Double](x: Double)(implicit num: Integral[Double]): Double =
    sum(x, num.fromInt(0), 1e-6)

  override def main(args: Array[String]): Unit = {
    val x = 1L
    println(arctg(x))
  }
}
