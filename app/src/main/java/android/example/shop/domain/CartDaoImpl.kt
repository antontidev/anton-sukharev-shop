package android.example.shop.domain

import javax.inject.Inject

class CartDaoImpl @Inject constructor(
    /**
     * There should be the room database instance
     */
): CartDao {

    private val productsList: List<RemoteProduct> = listOf()
    /**
     * Calculate price of all RemoteProducts in shopping cart
     * @return price
     */
    override fun calcProductsPrice(): Double {
        var productPrices: Double = 0.0

        productsList.forEach {
            val salePrice = it.applyDiscount()
            productPrices += salePrice
        }
        return productPrices
    }

    override fun addToCart(product: RemoteProduct) {
        TODO("Not yet implemented")
    }
}