package android.example.shop.domain.interactor

import android.example.shop.domain.CartDao
import javax.inject.Inject

class GetCartProductsCountUseCase @Inject constructor(
    private val cartDao: CartDao
) {
    operator fun invoke() = cartDao.getCartProductsCount()
}