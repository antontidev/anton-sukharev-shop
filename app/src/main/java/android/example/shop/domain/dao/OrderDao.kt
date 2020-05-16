package android.example.shop.domain.dao

import android.example.shop.domain.FullOrder
import android.example.shop.domain.Order

interface OrderDao {
    suspend fun createOrder(author: String, order: Order)

    suspend fun getOrders(author: String): List<FullOrder>
}