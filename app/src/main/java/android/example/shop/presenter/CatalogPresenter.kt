package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.ViewedProductDao
import android.example.shop.domain.interactor.GetCategoriesUseCase
import android.example.shop.presenter.view.CatalogView
import javax.inject.Inject

class CatalogPresenter @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val viewedProductDao: ViewedProductDao
) : BasePresenter<CatalogView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
        setRecentlyVisited()
    }

    fun setData() {
        launch {
            val categories = getCategoriesUseCase()
            viewState.setCategories(categories)
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