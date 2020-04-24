package android.example.shop.domain.model

data class TestShoppingCartItemModel(
    var name: String = "",
    var description: String = "",
    var fullDescription: String = "",
    var price: Double = 0.0,
    var id: Int = -1
)