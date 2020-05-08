package android.example.shop.domain.interactor

import android.example.shop.domain.dao.FavoriteDao
import javax.inject.Inject

class GetFavoriteProductsUseCase @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    operator fun invoke() = favoriteDao.getProducts()
}