package android.example.shop.domain.model

/**
 * Model for creating order
 */
data class CreateOrderModel(
    var firstName: String = "",
    var lastName: String = "",
    var middleName: String = "",
    var phone: String = ""
)