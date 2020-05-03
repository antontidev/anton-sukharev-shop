package android.example.shop.domain

interface CartDao {
    fun calcProductsPrice(): Double

    fun addToCart(product: RemoteProduct)
}