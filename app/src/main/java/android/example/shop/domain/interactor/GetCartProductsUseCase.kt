package android.example.shop.domain.interactor

import android.example.shop.domain.CartDao
import android.example.shop.domain.RemoteProduct
import javax.inject.Inject

class GetCartProductsUseCase @Inject constructor(
    private val cartDao: CartDao
) {
    operator fun invoke(): List<RemoteProduct> {
        return cartDao.getProducts()
    }
}