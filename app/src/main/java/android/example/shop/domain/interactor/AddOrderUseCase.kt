package android.example.shop.domain.interactor

import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import javax.inject.Inject

class AddOrderUseCase @Inject constructor(
    private val mainApi: MainApi,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke(product: RemoteProduct) {
        // mainApi.addProducgetUserUseCase(), product)
    }
}