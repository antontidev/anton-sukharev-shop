import android.example.shop.Product
import android.example.shop.obsolete.ConsolePricePrinter
import android.example.shop.obsolete.FilePricePrinter
import android.example.shop.obsolete.PricePrinter
import org.junit.Test

class FourthHomeTask {

    @Test
    fun consolePrintTest() {

        val iphoneCase = Product(price = 123.5, salePercent = 30, productName = "IPhone case")

        val pricePrinter: PricePrinter =
            ConsolePricePrinter()

        val discountIphoneCasePrice = iphoneCase.calcDiscountPrice()
        pricePrinter.print(discountIphoneCasePrice)
    }

    @Test
    fun filePrintTest() {
        val samsungCase = Product(price = 250.5, salePercent = 20, productName = "Samsung case")

        /**
         * File price.txt placed at ../app/price.txt directory
         */
        val pricePrinter: PricePrinter =
            FilePricePrinter("price.txt")

        val discountSamsungCasePrice = samsungCase.calcDiscountPrice()
        pricePrinter.print(discountSamsungCasePrice)
    }
}
