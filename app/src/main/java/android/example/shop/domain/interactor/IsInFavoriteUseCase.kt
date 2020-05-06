package android.example.shop.domain.interactor

import android.example.shop.domain.FavoriteDao
import android.example.shop.domain.RemoteProduct
import javax.inject.Inject

class IsInFavoriteUseCase @Inject constructor(
    private val favoriteDao: FavoriteDao
){
    operator fun invoke(product: RemoteProduct): Boolean {
        val products = favoriteDao.getProducts()

        return products.contains(product)
    }
}