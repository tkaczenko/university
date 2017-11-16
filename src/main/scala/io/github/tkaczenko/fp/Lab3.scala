/**
  * Created by tkaczenko on 27.04.17.
  */
object Lab3 extends App {
  def isEven(number: Int) = number % 2 == 0

  def isOdd(number: Int) = !isEven(number)

  def getEvens(list: List[Int]) = list.count(isEven)
  def getOdds(list: List[Int]) = list.count(isOdd)

  def getTouple(list: List[Int]) = (getEvens(list), getOdds(list))

  override def main(args: Array[String]): Unit = {
    val list = List.fill(15)(100).map(scala.util.Random.nextInt)
    println("Generated list")
    list.foreach(println)
    println("Touple(Even, Odd): " + getTouple(list))
  }
}
