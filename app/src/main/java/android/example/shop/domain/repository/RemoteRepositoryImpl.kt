package android.example.shop.domain.repository

import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val mainApi: MainApi
): RemoteRepository {

    override suspend fun getAllProducts(): List<RemoteProduct> {
        var list: List<RemoteProduct> = listOf()
//        try {
//            val response = mainApi.allProducts("default")
//            list = response.body()!!
//        } catch (t: Throwable) {
//
//        }
        return list
    }
}