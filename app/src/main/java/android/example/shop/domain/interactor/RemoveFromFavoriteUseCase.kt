package android.example.shop.domain.interactor

import android.example.shop.domain.FavoriteDao
import android.example.shop.domain.RemoteProduct
import javax.inject.Inject

class RemoveFromFavoriteUseCase @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    operator fun invoke(product: RemoteProduct) = favoriteDao.removeProduct(product)

}