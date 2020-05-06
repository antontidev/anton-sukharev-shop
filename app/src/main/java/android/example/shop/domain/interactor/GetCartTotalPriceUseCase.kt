package android.example.shop.domain.interactor

import android.example.shop.domain.CartDao
import javax.inject.Inject

class GetCartTotalPriceUseCase @Inject constructor(
    private val cartDao: CartDao
) {
    operator fun invoke(): Double {
        return cartDao.calcProductsPrice()
    }
}