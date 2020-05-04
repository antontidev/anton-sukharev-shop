package android.example.shop.domain.interactor

import android.example.shop.domain.CartDao
import android.example.shop.domain.RemoteProduct
import javax.inject.Inject

class RemoveProductFromCartUseCase @Inject constructor(
    private val cartDao: CartDao
) {
    operator fun invoke(product: RemoteProduct) {
        cartDao.removeFromCart(product)
    }
}