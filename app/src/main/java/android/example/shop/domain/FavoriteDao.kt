package android.example.shop.domain

import androidx.lifecycle.LiveData

interface FavoriteDao {
    fun addToFavorite(product: RemoteProduct)

    fun removeFromFavorite(product: RemoteProduct)

    fun getFavoriteProducts(): List<RemoteProduct>

    fun getFavoriteProductsCount(): LiveData<Int>
}