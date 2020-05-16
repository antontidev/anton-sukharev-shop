package android.example.shop.domain.model

import android.example.shop.domain.Order

/**
 * Model for creating order
 */
data class CreateOrderModel(
    var firstName: String = "",
    var lastName: String = "",
    var phone: String = "",
    var paymentType: Order.PaymentType = Order.PaymentType.CardOnReceiving,
    var item: List<Order.Item> = listOf()
)