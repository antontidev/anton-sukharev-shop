package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.*
import android.view.MenuItem
import moxy.MvpPresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val addProductToFavoriteUseCase: AddProductToFavoriteUseCase,
    private val addProductToViewedUseCase: AddProductToViewedUseCase,
    private val removeProductFromFavoriteUseCase: RemoveFromFavoriteUseCase,
    private val isInFavoriteUseCase: IsInFavoriteUseCase
): MvpPresenter<DescriptionView>() {
    fun addToCart(product: RemoteProduct) = addProductToCartUseCase(product)

    fun addToFavorite(product: RemoteProduct) = addProductToFavoriteUseCase(product)

    fun removeFromFavorite(product: RemoteProduct) = removeProductFromFavoriteUseCase(product)

    fun addToViewed(product: RemoteProduct) = addProductToViewedUseCase(product)

    fun inFavorite(product: RemoteProduct) = isInFavoriteUseCase(product)

    private fun showDetail() {
        viewState.showDetail()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        showDetail()
    }
}