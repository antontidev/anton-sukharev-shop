package android.example.shop.domain.interactor

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.CartDao
import javax.inject.Inject

class GetCartProductsUseCase @Inject constructor(
    private val cartDao: CartDao
) {
    operator fun invoke(): List<RemoteProduct> {
        return cartDao.getProducts()
    }
}