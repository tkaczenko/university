case class UniformDistribution(a: Int = 25173,
                               b: Int = 13749,
                               c: Int = 65536,
                               seed: Int = _) {
  private var _current: Int = seed

  def next(): Int = {
    return current = (a * seed + b) % c
  }

  def current: Int = _current
}
