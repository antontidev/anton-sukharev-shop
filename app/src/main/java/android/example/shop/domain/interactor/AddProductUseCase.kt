package android.example.shop.domain.interactor

import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val mainApi: MainApi
) {
    suspend operator fun invoke(category: String, product: RemoteProduct) =
        mainApi.addProductToCategory("Sukharev", category, product)
}