package android.example.shop.domain

import android.example.shop.domain.dao.OrderDao
import javax.inject.Inject

class OrderDaoImpl @Inject constructor(
    private val mainApi: MainApi
) : OrderDao {
    override suspend fun createOrder(author: String, order: Order) =
        mainApi.createOrder(author, order)

    override suspend fun getOrders(author: String) = mainApi.allOrders(author)
}