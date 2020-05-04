package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.GetErrorUseCase
import android.example.shop.domain.interactor.GetProductsUseCase
import java.net.ConnectException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ProductsPresenter @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getErrorUseCase: GetErrorUseCase
) : BasePresenter<ProductsView>() {
    private var data: List<RemoteProduct> = listOf()

    fun setData() {
        launch {
            try {
                data = getProductsUseCase.getProducts()

                viewState.setProducts(data)
            } catch(e: ConnectException) {
                viewState.showError("No internet")
            }
        }
    }

    private fun checkErrors() {
        if (getErrorUseCase.errorsCount() > 0) {
            val error = getErrorUseCase()
            viewState.showError(error.getMessage())
        }
    }

    fun showProductDetail(item: RemoteProduct) {
        viewState.navigateToProductDetail(item)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }
}