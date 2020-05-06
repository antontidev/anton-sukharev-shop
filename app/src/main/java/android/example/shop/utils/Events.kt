package android.example.shop.utils

data class CartChangedEvent(
    val count: Int
)

data class FavoriteChangedEvent(
    val count: Int
)