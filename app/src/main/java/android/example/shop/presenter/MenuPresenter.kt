package android.example.shop.presenter

import android.example.shop.domain.dao.ViewedProductDao
import android.example.shop.domain.interactor.GetFavoriteProductsUseCase
import android.example.shop.presenter.view.MenuView
import moxy.MvpPresenter
import javax.inject.Inject

class MenuPresenter @Inject constructor(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val viewedProductDao: ViewedProductDao
): MvpPresenter<MenuView>() {

    fun setFavoriteBadge(count: Int) {
        viewState.showFavoriteBadge(count)
    }

    fun setCartBadge(count: Int) {
        viewState.showCartBadge(count)
    }

    fun setViewedProducts() {
        val products = viewedProductDao.getAllProducts()

        if (products.size > 40) {
            viewedProductDao.clear()
        }
    }
}