package android.example.shop.domain.dao

import android.example.shop.domain.RemoteProduct

interface FavoriteDao {
    fun addProduct(product: RemoteProduct)

    fun removeProduct(product: RemoteProduct)

    fun getProducts(): List<RemoteProduct>
}