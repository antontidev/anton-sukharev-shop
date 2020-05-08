package android.example.shop.domain.interactor

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.FavoriteDao
import javax.inject.Inject

class AddProductToFavoriteUseCase @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    operator fun invoke(product: RemoteProduct) {
        favoriteDao.addProduct(product)
    }
}