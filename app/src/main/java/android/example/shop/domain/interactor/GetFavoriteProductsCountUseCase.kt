package android.example.shop.domain.interactor

import android.example.shop.domain.FavoriteDao
import javax.inject.Inject

class GetFavoriteProductsCountUseCase @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    operator fun invoke() = favoriteDao.getFavoriteProductsCount()
}