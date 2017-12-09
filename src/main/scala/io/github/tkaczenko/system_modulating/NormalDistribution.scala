class NormalDistribution {
  private val _sigma = 8
  private val _expectedValue = 8
  private var _value_A = 0
  private var _value_B = 0
  private var _value_C = 0
  private var _currentValue = 0
  private var _minZ = .0
  private var _maxZ = .0
  private var _Fm = .0

  NormalDistribution(seed: Int) {
    InitGenerator(25173, 13749, 65536, seed)
  }

  NormalDistribution(value_A: Int, value_B: Int, value_C: Int, seed: Int) {
    InitGenerator(value_A, value_B, value_C, seed)
  }

  private def InitGenerator(a: Int, b: Int, c: Int, seed: Int) = {
    _value_A = a
    _value_B = b
    _value_C = c
    _currentValue = seed
    _minZ = _expectedValue - 5 * _sigma
    _maxZ = _expectedValue + 5 * _sigma
    _Fm = ProbabilityDensityFunction(_expectedValue)
  }

  def GetNext: Double = {
    var w = .0
    var z = .0
    var y = .0
    do {
      val newValue = (_value_A * _currentValue + _value_B) % _value_C
      w = newValue.toDouble / _value_C
      z = _currentValue.toDouble / _value_C * (_maxZ - _minZ) + _minZ
      y = _Fm * w
      _currentValue = newValue
    } while ( {
      y > ProbabilityDensityFunction(z)
    })
    z
  }

  private def ProbabilityDensityFunction(x: Double) = (1 / Math.Sqrt(2 * Math.PI) * _sigma) * Math.Exp(-(Math.Pow(x - _expectedValue, 2) / (2 * Math.Pow(_sigma, 2))))
}
