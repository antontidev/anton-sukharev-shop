package android.example.shop

import org.junit.Test
import java.io.Console

class FifthHomeTask {

    @Test
    fun testShoppingCart() {
        val iphoneCase = Product(price = 123.5, salePercent = 30)
        val samsungCase = Product(price = 250.5, salePercent = 20)

        // Creating shopping cart
        val shoppingCart = ShoppingCart(listOf(iphoneCase, samsungCase))

        // Start of printing shopping cart products price
        val pricePrinter = ConsolePricePrinter()
        val allProductsPrice = shoppingCart.calcProductsPrice()

        pricePrinter.print(allProductsPrice)
        // End

        // Start printing all products prices
        val shoppingCartPrinterConsole = ShoppingCartPrinterConsole()

        shoppingCartPrinterConsole.print(shoppingCart)

        val shoppingCartPrinterFile = ShoppingCartPrinterFile("shoppingCart.txt")

        shoppingCartPrinterFile.print(shoppingCart)
        // End
    }
}