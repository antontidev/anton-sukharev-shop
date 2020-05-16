package android.example.shop.domain.interactor

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.CartDao
import javax.inject.Inject

class isInCartUseCase @Inject constructor(
    private val cartDao: CartDao
) {
    operator fun invoke(product: RemoteProduct): Boolean {
        val products = cartDao.getProducts()

        return products.contains(product)
    }
}