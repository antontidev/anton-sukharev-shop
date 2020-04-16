import android.example.shop.obsolete.ConsolePricePrinter
import android.example.shop.obsolete.PricePrinter
import android.example.shop.Product
import org.junit.Test

class SixLesson {

    @Test
    fun consolePrintTest() {
        val pricePrinter: PricePrinter =
            ConsolePricePrinter()

        val presenter = Presenter()

        presenter.printPrice()
    }

    class Presenter {
        private val iphoneCase = Product(price = 123.5, salePercent = 30, productName = "IPhone case")
        private val samsungCase = Product(price = 250.5, salePercent = 20, productName = "Samsung case")

        private val productList = listOf(iphoneCase, samsungCase)

        fun printPrice() {
            val pricePrinter =
                ConsolePricePrinter()

            productList.forEach {
                pricePrinter.print(it.calcDiscountPrice())
            }
        }

        fun productNamePrint() {
            val pricePrinter =
                ConsolePricePrinter()

            productList.forEach {
                pricePrinter.print(it.getProductName())
            }
        }

        fun printAllProducts() {
            val pricePrinter =
                ConsolePricePrinter()

            productList.forEach {
                pricePrinter.print(it)
            }
        }
    }
}
