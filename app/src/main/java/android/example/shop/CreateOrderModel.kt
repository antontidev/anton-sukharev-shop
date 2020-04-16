package android.example.shop

/**
 * Model for creating order
 */
data class CreateOrderModel(
    var firstName: String = "",
    var secondName: String = "",
    var middleName: String = "",
    var phone: String = ""
)