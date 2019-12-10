package fibonacci.domain

class Fibonacci {

  fun calculate(n: Int): Int {
    return when (n) {
      0 -> 0
      1 -> 1
      else -> calculate(n - 1) + calculate(n - 2)
    }
  }

}