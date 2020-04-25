package android.example.shop

import org.junit.Test
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    /**
     * Форматирует цену
     */
//    fun formatPrice(price: Int = 0, measure: String): String {
//        // Форматируем цену
//        return "Price is $price/$measure"
//    }

    fun formatPriceWithDiscount(price: Double = 0.0, measure: String = "шт", discount: Int = 0) {
        val totalPrice = price * (1 - discount / 100.0)
        var roundedPrice: Any = totalPrice
        when (totalPrice % 1.0 != 0.0) {
            false -> roundedPrice = totalPrice.roundToInt()
        }

        when (discount) {
            0 -> print("$roundedPrice/$measure")
            else -> print("$roundedPrice/$measure (скидка $discount%)")
        }
    }

    fun doSomethingShort(arg1: Int, arg2: String): String = "Hello World! $arg1, $arg2"

    @Test
    fun addition_isCorrect() {
        println(formatPriceWithDiscount(120.0, "кг", 5))
    }

    fun sumNumbers(num1: Double, num2: Double): Double {
        return num1 + num2
    }

    fun solveQuadraticEquation(a: Double, b: Double = 0.0, c: Double = 0.0): Pair<Double, Double> {
        val d = b * b - 4 * c * a
        val x1 = (-b + sqrt(d)) / 2 * a
        val x2 = (-b - sqrt(d)) / 2 * a
        return Pair(x1, x2)
    }
}
