import java.io.{File, PrintWriter}

import scala.collection.mutable.ListBuffer

object UniformGenerator extends App {
  override def main(args: Array[String]): Unit = {
    val r = 0
    val numbersCount = 10000
    val generatorSeed = 27000
    var generatedValues = ListBuffer[Double]()
    val uniformDistribution: UniformDistribution = UniformDistribution(seed = generatorSeed)
    for (i <- 0 until numbersCount) {
      generatedValues += uniformDistribution.next()
    }
    val writer = new PrintWriter(new File("generated.txt"))
    generatedValues.foreach(generatedValue => {
      writer.write(generatedValue + "\n")
    })

    val minValue = generatedValues.min
    val maxValue = generatedValues.max
    println("Min value = " + minValue)
    println("Max value = " + maxValue)

    var intervalsCount: Int = 3.3 * math.log10(numbersCount)
    val intervalCountValues = ListBuffer[Double]()
    for (i <- 0 until intervalsCount) {
      intervalCountValues += 0
    }

    println("Number of intervals: " + intervalsCount)
    val step: Double = (maxValue - minValue) / intervalsCount
    println("Step " + step)
    intervalCountValues.foreach(value => {
      val index = (value - minValue - 1) / step
      intervalCountValues(index) ++;
    })

    val probability = 1.0 / intervalsCount
    val chiSquare: Double = 0
    println("Frequancy analysis:")
    for (i <- 0 until intervalsCount) {
      println(minValue + step * i + " " + minValue + step * (i + 1), intervalCountValues(i))
      chiSquare = chiSquare + (((intervalCountValues(i) - probability * numbersCount)
        * (intervalCountValues(i) - probability * numbersCount)) / probability * numbersCount)
    }

    val chiSquareCritical = ChiSquareCritical.GetValue(intervalsCount - r - 1, SignificanceLevel.SL_0_05)
    Console.WriteLine("\n\rXн = {0:F}    {1}    Xcr({2}; 0.05) = {3:F}", chiSquare,
      if (ok) "<=" else ">", intervalsCount - r - 1, chiSquareCritical)
  }
}

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
}