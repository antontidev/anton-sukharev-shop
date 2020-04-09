package android.example.shop

import java.io.File

interface PricePrinter {

    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)

    fun print(name: String)

    fun print(product: Product)
}

class ConsolePricePrinter : PricePrinter {
    override fun print(price: Double) {
        println(price.toString())
    }

    override fun print(name: String) {
        println(name)
    }

    override fun print(product: Product) {
        println("${product.getProductName()}: ${product.calcDiscountPrice()}")
    }

}

class FilePricePrinter(
    private var filePath: String
) : PricePrinter {
    private var file: File = File(filePath)

    /**
     * Open and clear file
     */
    init {
        file.writeText("")
    }

    override fun print(price: Double) {
        file.appendText(price.toString() + System.lineSeparator())
    }

    override fun print(name: String) {
        file.appendText(name + System.lineSeparator())
    }

    override fun print(product: Product) {
        file.appendText("${product.getProductName()}: ${product.calcDiscountPrice()}")
    }
}