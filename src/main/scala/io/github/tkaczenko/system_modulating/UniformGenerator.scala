import java.io.{File, PrintWriter}

import io.github.tkaczenko.system_modulating.{ChiSquareCritical, SignificanceLevel}

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

    var intervalsCount: Int = (3.3 * math.log10(numbersCount)).toInt
    val intervalCountValues = ListBuffer[Double]()
    for (i <- 0 until intervalsCount) {
      intervalCountValues += 0
    }

    println("Number of intervals: " + intervalsCount)
    val step: Double = (maxValue - minValue) / intervalsCount
    println("Step " + step)
    intervalCountValues.foreach(value => {
      val index = (value - minValue - 1) / step
      intervalCountValues(index.toInt) += 1
    })

    val probability = 1.0 / intervalsCount
    var chiSquare: Double = 0
    println("Frequancy analysis:")
    for (i <- 0 until intervalsCount) {
      println(minValue + step * i + " " + minValue + step * (i + 1), intervalCountValues(i))
      chiSquare = chiSquare + (((intervalCountValues(i) - probability * numbersCount)
        * (intervalCountValues(i) - probability * numbersCount)) / probability * numbersCount)
    }

    val chiSquareCritical = ChiSquareCritical.GetValue(intervalsCount - r - 1, SignificanceLevel.SL_0_05)
    println("\n\rXн = {0:F}    {1}    Xcr({2}; 0.05) = {3:F}", chiSquare,
      if (chiSquare < chiSquareCritical) "<=" else ">", intervalsCount - r - 1, chiSquareCritical)
  }
}