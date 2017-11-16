/**
  * Created by tkaczenko on 25.04.17.
  */
object Lab2 extends App {
  def isEven(number: Int) = number % 2 == 0

  def isOdd(number: Int) = !isEven(number)

  def countEvenNumbers(list: List[Int]): Int = {
    list match {
      case Nil => 0
      case h :: t if isEven(h) => 1 + countEvenNumbers(t)
      case _ :: t => countEvenNumbers(t)
    }
  }

  def countOddNumbers(list: List[Int]): Int = {
    list match {
      case Nil => 0
      case h :: t if isOdd(h) => 1 + countOddNumbers(t)
      case _ :: t => countOddNumbers(t)
    }
  }

  def calculateEvenAndOddNumbers(list: List[Int]) = (countEvenNumbers(list), countOddNumbers(list))

  def listToString(list: List[Int]): String = list match {
    case Nil => ""
    case h :: t => h + "\n" + listToString(t)
  }

  override def main(args: Array[String]): Unit = {
    val list = List.fill(15)(100).map(scala.util.Random.nextInt)
    println("Generated list")
    println(listToString(list))
    print("Touple(Even, Odd): " + calculateEvenAndOddNumbers(list))
  }
}
