package android.example.shop

class ShoppingCart(
    var productList: List<Product>
) {
    /**
    * Calculate price of all products in shopping cart
     * @return price
     */
    fun calcProductsPrice(): Double {
        var productPrices: Double = 0.0

        productList.forEach {
            val salePrice = it.calcDiscountPrice()
            productPrices += salePrice
        }
        return productPrices
    }
}