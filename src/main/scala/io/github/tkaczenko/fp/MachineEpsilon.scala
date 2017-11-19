package lab1

/**
  * Created by 1 on 21.10.2017.
  */
object MachineEpsilon extends App {

  override def main(args: Array[String]): Unit = {
    var machineEps: Double = 1.0d
    var machineEps1: Float = 1.0f
    var iter: Int = 0

    var start: Double = System.nanoTime()
    do {
      machineEps /= 2.0d
      iter += 1
    } while ((1.0d + machineEps) > 1.0d)
    var stop: Double = System.nanoTime()
    machineEps *= 2.0D
    iter -= 1
    printf("Double:\nCalculated machine epsilon is %e by iterations %d \n" +
      "and took %f ns\n", machineEps, iter, stop - start)

    iter = 0
    start = System.nanoTime()
    do {
      machineEps1 /= 2.0f
      iter += 1
    } while ((1.0f + machineEps1) > 1.0f)
    stop = System.nanoTime()
    machineEps1 *= 2.0f
    iter -= 1
    printf("Double:\nCalculated machine epsilon is %e by iterations %d \n" +
      "and took %f ns\n", machineEps1, iter, stop - start)
  }
}
