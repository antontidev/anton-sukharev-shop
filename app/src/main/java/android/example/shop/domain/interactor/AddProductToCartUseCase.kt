package android.example.shop.domain.interactor

import android.example.shop.domain.MainApi
import android.example.shop.domain.ViewedProductDao
import kotlinx.coroutines.delay
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
   private val mainApi: MainApi,
   private val viewedProductDao: ViewedProductDao
) {
    suspend operator fun invoke() {
        delay(1000)
    }
}