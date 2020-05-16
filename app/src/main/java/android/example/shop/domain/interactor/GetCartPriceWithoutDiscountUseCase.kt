package android.example.shop.domain.interactor

import android.example.shop.domain.dao.CartDao
import javax.inject.Inject

class GetCartPriceWithoutDiscountUseCase @Inject constructor(
    private val cartDao: CartDao
) {
    operator fun invoke() = cartDao.calcProductsPrice()
}