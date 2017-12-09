import java.io.{File, PrintWriter}

import scala.collection.mutable.ListBuffer

object UniformGenerator extends App {
  override def main(args: Array[String]): Unit = {
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

    var intervalsCount =
  }
}