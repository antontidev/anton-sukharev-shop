package android.example.shop.domain

import android.example.shop.domain.model.TestShoppingCartItemModel

interface ViewedProductDao {

    /**
     * Save product as viewed
     */
    fun addProduct(product: TestShoppingCartItemModel)

    /**
     * Get all viewed product list
     */
    fun getAllProducts(): List<TestShoppingCartItemModel>

    /**
     * Clear list
     */
    fun clear()
}