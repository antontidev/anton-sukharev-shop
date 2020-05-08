package android.example.shop.domain.dao

import android.example.shop.domain.RemoteProduct

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