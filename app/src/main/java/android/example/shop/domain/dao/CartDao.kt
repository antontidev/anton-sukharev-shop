package android.example.shop.domain.dao

import android.example.shop.domain.RemoteProduct

interface CartDao {
    fun calcProductsPrice(): Double

    fun calcDiscountPrice(): Double

    fun calcProductsPriceWithDiscount(): Double

    fun getProductsCount(): Int

    fun addProduct(product: RemoteProduct)

    fun removeProduct(product: RemoteProduct)

    fun getProducts(): List<RemoteProduct>
}