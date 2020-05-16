package android.example.shop.domain.interactor

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.CartDao
import javax.inject.Inject

class removeProductFromCartUseCase @Inject constructor(
    private val cartDao: CartDao
) {
    operator fun invoke(product: RemoteProduct) = cartDao.removeProduct(product)
}