package android.example.shop

import java.io.File

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
        println(price.toString())
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
        File(filePath).appendText(price.toString() + System.lineSeparator())
    }
}