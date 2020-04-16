package android.example.shop

import android.example.shop.obsolete.ConsolePricePrinter
import android.example.shop.obsolete.ShoppingCartPrinterConsole
import android.example.shop.obsolete.ShoppingCartPrinterFile
import org.junit.Test

class FifthHomeTask {

    @Test
    fun testShoppingCart() {
        val iphoneCase = Product(
            price = 123.5,
            salePercent = 30,
            productName = "IPhone case"
        )
        val samsungCase = Product(
            price = 250.5,
            salePercent = 20,
            productName = "Samsung case"
        )

        // Creating shopping cart
        val shoppingCart =
            ShoppingCart(listOf(iphoneCase, samsungCase))

        // Start of printing shopping cart products price
        val pricePrinter = ConsolePricePrinter()
        val allProductsPrice = shoppingCart.calcProductsPrice()

        pricePrinter.print(allProductsPrice)
        // End

        // Start printing all products prices
        val shoppingCartPrinterConsole =
            ShoppingCartPrinterConsole()

        shoppingCartPrinterConsole.print(shoppingCart)

        val shoppingCartPrinterFile =
            ShoppingCartPrinterFile("shoppingCart.txt")

        shoppingCartPrinterFile.print(shoppingCart)
        // End
    }
}