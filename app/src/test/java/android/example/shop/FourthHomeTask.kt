import android.example.shop.ConsolePricePrinter
import android.example.shop.FilePricePrinter
import android.example.shop.PricePrinter
import android.example.shop.Product
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
