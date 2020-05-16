package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.FavoriteDao
import android.example.shop.presenter.view.FavoriteView
import javax.inject.Inject

class FavoritePresenter @Inject constructor(
    private val favoriteDao: FavoriteDao
) : BasePresenter<FavoriteView>() {

    fun setFavoriteMenu(count: Int) {
        val isVisible = count > 0
        viewState.showFavoriteMenu(isVisible)
    }

    fun showProductDetail(product: RemoteProduct) {
        viewState.navigateToProductDetail(product)
    }

    private fun setFavoriteData() {
        val products = favoriteDao.getProducts()

        viewState.showFavoriteProducts(products)
    }

    fun showDeleteAllFavoriteDialog() {
        viewState.deleteAllFavorite()
    }

    fun deleteAllFavorite() {
        favoriteDao.removeAllProducts()
    }

    private fun firstSetFavoriteMenu() {
        val products = favoriteDao.getProducts()

        setFavoriteMenu(products.size)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        setFavoriteData()
    }
}