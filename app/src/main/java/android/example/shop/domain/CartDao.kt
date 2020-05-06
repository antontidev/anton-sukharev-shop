package android.example.shop.domain

import androidx.lifecycle.LiveData

interface CartDao {
    fun calcProductsPrice(): Double

    fun addProduct(product: RemoteProduct)

    fun removeProduct(product: RemoteProduct)

    fun getProducts(): List<RemoteProduct>
}