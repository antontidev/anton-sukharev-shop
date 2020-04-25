package android.example.shop

class ShoppingCart(
    private val productsList: List<Product>
) {
    /**
     * Hide productsList from internal world to preserve direct access to data
     */
    fun forEachProduct(action: (Product) -> Unit) {
        productsList.forEach {
            action(it)
        }
    }

    /**
     * Calculate price of all products in shopping cart
     * @return price
     */
    fun calcProductsPrice(): Double {
        var productPrices: Double = 0.0

        productsList.forEach {
            val salePrice = it.calcDiscountPrice()
            productPrices += salePrice
        }
        return productPrices
    }
}