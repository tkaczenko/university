import java.io.{File, PrintWriter}

import scala.collection.mutable.ListBuffer

object NormalGenerator extends App {
  override def main(args: Array[String]): Unit = {
    val r = 2
    val numbersCount = 10000
    val generatorSeed = 8000
    val E = 0.01

    val generatedValues = ListBuffer[Double]()
    val uniformDistribution = NormalDistribution(generatorSeed)
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

    generatedValues.foreach(value => intervalCountValues((value - minValue) / step - E) ++;

    var expectedValue = 0
    for (i <- 0 until intervalsCount) {
      expectedValue += (minValue + step * i + step / 2) * intervalCountValues(i)
    }
    expectedValue = expectedValue / numbersCount
    println("Mat oc " + expectedValue)

    var sigma: Double = 0
    for (i <- 0 until intervalsCount) {
      sigma += math.pow((minValue + step * i + step / 2) - expectedValue, 2) * intervalCountValues(i)
    }
    sigma = Math.Sqrt(sigma / (numbersCount - 1))
    println("Ozenka srend " + sigma)

    val chiSquare: Double = 0
    for (i <- intervalsCount) {
      val x1 = ((minValue + step * i) - expectedValue) / sigma
      val x2 = ((minValue + step * (i + 1)) - expectedValue) / sigma
      val probability = Helper.GetLaplasFunction(x2) - Helper.GetLaplasFunction(x1)
      chiSquare += math.pow(intervalCountValues(i) - probability * numbersCount, 2) / (probability * numbersCount)
    }

    val chiSquareCritical = ChiSquareCritical.GetValue(intervalsCount - r - 1, SignificanceLevel.SL_0_05)
    Console.WriteLine("\n\rXн = {0:F}    {1}    Xcr({2}; 0.05) = {3:F}", chiSquare,
      if (ok) "<=" else ">", intervalsCount - r - 1, chiSquareCritical)
  }
}