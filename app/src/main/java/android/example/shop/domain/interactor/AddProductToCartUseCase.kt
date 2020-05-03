package android.example.shop.domain.interactor

import android.example.shop.domain.CartDaoImpl
import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.ViewedProductDao
import kotlinx.coroutines.delay
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
   private val cartDaoImpl: CartDaoImpl
) {
    operator fun invoke(product: RemoteProduct) {
        cartDaoImpl.addToCart(product)
    }
}