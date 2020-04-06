package android.example.shop

import java.io.File

interface ShoppingCartPrinter {
    /**
     * Prints every product in shopping cart.
     */
    fun print(shoppingCart: ShoppingCart)
}

class ShoppingCartPrinterConsole : ShoppingCartPrinter {
    override fun print(shoppingCart: ShoppingCart) {
        var pricePrinter = ConsolePricePrinter()

        shoppingCart.productList.forEach {
            pricePrinter.print(it.calcDiscountPrice())
        }
    }
}

class ShoppingCartPrinterFile(
    val fileName: String
) : ShoppingCartPrinter {
    override fun print(shoppingCart: ShoppingCart) {
        var pricePrinter = FilePricePrinter(fileName)

        shoppingCart.productList.forEach() {
            pricePrinter.print(it.calcDiscountPrice())
        }
    }
}