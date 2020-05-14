package android.example.shop.domain

import android.example.shop.domain.dao.CartDao
import android.example.shop.utils.CartChangedEvent
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class CartDaoImpl @Inject constructor(
    /**
     * There should be the room database instance
     */
): CartDao {
    /**
     * This is deprecated
     */

    private val productsList: MutableList<RemoteProduct> = mutableListOf()
    /**
     * Calculate price of all RemoteProducts in shopping cart
     * @return price
     */
    override fun calcProductsPrice(): Double {
        var productsPrice = 0.0

        productsList.forEach {cartProduct ->
            val productPriceWithDiscount = cartProduct.applyDiscount()
            productsPrice += productPriceWithDiscount
        }
        return productsPrice
    }

    override fun getProductsCount() = productsList.size

    override fun addProduct(product: RemoteProduct) {
        productsList.find {
            product == it
        } ?: productsList.add(product)
        EventBus.getDefault().post(CartChangedEvent(productsList.size))
    }

    override fun removeProduct(product: RemoteProduct) {
        productsList.remove(product)
        EventBus.getDefault().post(CartChangedEvent(productsList.size))
    }

    override fun getProducts(): List<RemoteProduct> = productsList
}