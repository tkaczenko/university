

object UniformGenerator extends App {
  override def main(args: Array[String]): Unit = {
    val numbersCount = 10000
    val generatorSeed = 27000
    var generatedValues = new List[Double]()
    val uniformDistribution: UniformDistribution = UniformDistribution(seed = generatorSeed)
    for (i <- 0 until numbersCount) {
      generatedValues += uniformDistribution.next()
    }
  }
}