package android.example.shop.domain

import androidx.lifecycle.LiveData

interface CartDao {
    fun calcProductsPrice(): Double

    fun addToCart(product: RemoteProduct)

    fun removeFromCart(product: RemoteProduct)

    fun getCartProductsCount(): LiveData<Int>

    fun getCartProducts(): List<RemoteProduct>
}