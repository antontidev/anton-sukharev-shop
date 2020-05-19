package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.ViewedProductDao
import android.example.shop.domain.interactor.AddProductUseCase
import android.example.shop.domain.interactor.GetCategoriesUseCase
import android.example.shop.presenter.view.CatalogView
import java.net.ConnectException
import javax.inject.Inject

class CatalogPresenter @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val viewedProductDao: ViewedProductDao,
    private val addProductUseCase: AddProductUseCase
) : BasePresenter<CatalogView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
        setRecentlyVisited()
    }


    fun showCreateProduct() {
        viewState.navigateToCreateProduct()
    }

    fun getRecentlyViewed(): List<RemoteProduct> {
        return viewedProductDao.getAllProducts()
    }
    fun setData() {
        launch {
            try {
                val categories = getCategoriesUseCase()
                viewState.setCategories(categories)
            }
            catch (e: ConnectException) {
                viewState.showError(e.message!!)
            }
        }
    }

    private fun setRecentlyVisited() {
        launch {
            val viewedProducts = viewedProductDao.getAllProducts()

            viewState.setRecentlyVisitedData(viewedProducts)
        }
    }

    fun showCategoryProducts(category: String) {
        viewState.navigateToCategory(category)
    }

    fun showProductDetail(product: RemoteProduct) {
        viewState.navigateToDetail(product)
    }

    fun createOrder() {
        viewState.navigateToOrder()
    }
}