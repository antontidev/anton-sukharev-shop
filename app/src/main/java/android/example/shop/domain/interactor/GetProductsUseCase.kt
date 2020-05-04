package android.example.shop.domain.interactor

import android.example.shop.domain.ErrorHandler
import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.model.ErrorModel
import android.util.Log
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val mainApi: MainApi,
    private val errorHandler: ErrorHandler
) {
    suspend fun getProducts(): List<RemoteProduct> {
        var list: List<RemoteProduct> = listOf()
        try {
            list =  mainApi.allProducts("default")
        } catch (e: ConnectException) {
            val errorModel: ErrorModel = ErrorModel(e)
            errorHandler.dispatchError(errorModel)
        }
        return list
    }
}