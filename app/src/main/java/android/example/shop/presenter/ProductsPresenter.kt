package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.GetCategoryProductsUseCase
import android.example.shop.domain.interactor.GetErrorUseCase
import android.example.shop.presenter.view.ProductsView
import java.net.ConnectException
import javax.inject.Inject

class ProductsPresenter @Inject constructor(
    private val getCategoryProductsUseCase: GetCategoryProductsUseCase,
    private val getErrorUseCase: GetErrorUseCase
) : BasePresenter<ProductsView>() {
    private var data: List<RemoteProduct> = listOf()

    fun setData(category: String) {
        launch {
            try {
                data = getCategoryProductsUseCase(category)

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
    }
}