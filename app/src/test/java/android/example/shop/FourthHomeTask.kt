import org.junit.Test
import java.io.File

class FourthHomeTask {

    @Test
    fun consolePrintTest() {

        val iphoneCase = Product(price = 123.5, salePercent = 30)

        val pricePrinter: PricePrinter = ConsolePricePrinter()

        val discountIphoneCasePrice = iphoneCase.calcDiscountPrice()
        pricePrinter.print(discountIphoneCasePrice)
    }

    @Test
    fun filePrintTest() {
        val samsungCase = Product(price = 250.5, salePercent = 20)

        /**
         * File price.txt placed at ../app/price.txt directory
         */
        val pricePrinter: PricePrinter = FilePricePrinter("price.txt")

        val discountSamsungCasePrice = samsungCase.calcDiscountPrice()
        pricePrinter.print(discountSamsungCasePrice)
    }
}

class Product(
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0
) {
    /**
     * @return price with applied discount determined by [salePercent]
     *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)
}

interface PricePrinter {

    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)
}

class ConsolePricePrinter : PricePrinter {
    override fun print(price: Double) {
        print(price.toString())
    }

}

class FilePricePrinter(
    private var filePath: String
) : PricePrinter {
    override fun print(price: Double) {
        File(filePath).writeText(price.toString())
    }

}