package android.example.shop.domain

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import kotlin.properties.Delegates

class CartDaoImpl @Inject constructor(
    /**
     * There should be the room database instance
     */
): CartDao {

    private val count = MutableLiveData<Int>()

    /**
     * This is deprecated
     */
    private val productsList: MutableList<RemoteProduct> = mutableListOf()
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
        productsList.add(product)
        count.value = productsList.size
    }

    override fun removeFromCart(product: RemoteProduct) {
        productsList.remove(product)
        count.value = productsList.size
    }

    override fun getCartProductsCount() = count

    override fun getCartProducts(): List<RemoteProduct> {
        return productsList
    }
}