package android.example.shop.domain.model

data class CreateProductModel(
    var name: String = "",
    var author: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var imageUrl: String = ""
)