import fibonacci.Fibonacci
import kotlin.test.Test
import kotlin.test.assertEquals

class FibonacciTest {

  @Test
  fun fibOf0() {
    val fibonacci = Fibonacci()
    assertEquals(
      0,
      fibonacci.calculate(0)
    )
  }

  @Test
  fun fibOf1() {
    val fibonacci = Fibonacci()
    assertEquals(
      1,
      fibonacci.calculate(1)
    )
  }

  @Test
  fun fibOf2() {
    val fibonacci = Fibonacci()
    assertEquals(
      1,
      fibonacci.calculate(2)
    )
  }

}