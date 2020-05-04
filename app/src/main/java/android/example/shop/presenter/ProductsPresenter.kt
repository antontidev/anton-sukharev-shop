package android.example.shop.presenter

import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.GetProductsUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ProductsPresenter @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
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

    fun showProductDetail(item: RemoteProduct) {
        viewState.navigateToProductDetail(item)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }
}