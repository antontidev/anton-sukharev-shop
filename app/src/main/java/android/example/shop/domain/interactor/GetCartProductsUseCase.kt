package android.example.shop.domain.interactor

import android.example.shop.domain.CartDaoImpl
import android.example.shop.domain.RemoteProduct
import javax.inject.Inject

class GetCartProductsUseCase @Inject constructor(
    private val cartImpl: CartDaoImpl
) {
    operator fun invoke(): List<RemoteProduct> {
        return listOf()
    }
}