package android.example.shop.domain

import android.example.shop.domain.model.TestShoppingCartItemModel

interface ViewedProductDao {

    /**
     * Save product as viewed
     */
    fun addProduct(product: RemoteProduct)

    /**
     * Get all viewed product list
     */
    fun getAllProducts(): List<RemoteProduct>

    /**
     * Clear list
     */
    fun clear()
}