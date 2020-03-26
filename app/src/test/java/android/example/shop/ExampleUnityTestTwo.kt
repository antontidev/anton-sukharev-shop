package android.example.shop

import org.junit.Test
import kotlin.math.roundToInt

class ExampleUnityTestTwo {

    private fun formatPrice(price: Double = 0.0, measure: String = "шт", discount: Int = 0) {
        val totalPrice = price * (1 - discount / 100.0)
        var roundedPrice: Any = totalPrice

        //Округляем до целого, если нет дробной части
        when (totalPrice % 1.0 == 0.0) {
            false -> roundedPrice = totalPrice.roundToInt()
        }

        //Выводим в зависимости от значения скидки
        when(discount) {
            0 -> print("$roundedPrice/$measure")
            else -> print("$roundedPrice/$measure (скидка $discount%)")
        }
    }

    @Test
    fun addition_isCorrect() {
        println(formatPrice(120.5, "кг", 5))
    }
}