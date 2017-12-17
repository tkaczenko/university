import java.io.{File, PrintWriter}

object Lab4 extends App {
  def EilerMethod(y0: Double, step: Double, filename: String): Unit = {
    val res = new StringBuilder
    val n = (180 / step).toInt
    var t = 0
    var Yn = y0
    var Yn0 = 0
    var Y = 0
    var newY = 0
    res.append(string.Format("t = {0:0.00}\tY = {1:0.000}\tY' =  {2:0.000}", t, Y, newY))
    var i = 0
    while (i
    <- 0 until n
    )
    {
      Y = Yn + Yn0 * step
      newY = Yn0 + step * (sigma * math.sin(w * t) - b1 * Yn0 - b2 * Yn0 * math.abs(Yn0) - c1 * Yn - c3 * (Yn * Yn * Yn))
      t = t + step
      res.append(s("t = {0:0.00}\tY = {1:0.000}\tY' =  {2:0.000}", t, Y, newY))
      Yn = Y
      Yn0 = newY
    }
    val writer = new PrintWriter(new File(filename))
    res.foreach(value => {
      writer.write(value)
    })
  }

  override def main(args: Array[String]): Unit = {
    val b1: Double = 0.04
    val b2: Double = 1.0
    val c1: Double = 1.1
    val c3: Double = -1.1
    val w: Double = 1.0
    val epsion: Double = 0.05
    val sigma: Double = 0.070
    val y0: Double = 0.179
    println("Введите шаг: ");
    double step = (StdIn.readLine().replace(".", ","));
    EilerMethod(y0, step, "n1.txt");
    println("Расчитаные значения y(t) и y'(t) для {0} значений сохранены в файл n1.txt", (int) (180 / step));
    EilerMethod(y0, step / 2, "n2.txt");
    println("Расчитаные значения y(t) и y'(t) для {0} значений сохранены в файл n2.txt", (int) (180 / (step / 2)));
  }
}