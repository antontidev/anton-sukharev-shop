package android.example.shop.domain.interactor

import android.example.shop.domain.MainApi
import android.example.shop.domain.Order
import javax.inject.Inject

class AddOrderUseCase @Inject constructor(
    private val mainApi: MainApi
) {
    suspend operator fun invoke(order: Order) {
        mainApi.createOrder("Sukharev", order)
    }
}