package android.example.shop.domain.interactor

import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import android.util.Log
import java.net.UnknownHostException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val mainApi: MainApi
) {

    suspend fun getProducts(): List<RemoteProduct> {
        return mainApi.allProducts("default")
    }
}