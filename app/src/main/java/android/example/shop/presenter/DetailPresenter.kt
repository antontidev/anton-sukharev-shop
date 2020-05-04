package android.example.shop.presenter

import android.content.SharedPreferences
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.AddProductToCartUseCase
import android.example.shop.domain.interactor.AddProductToFavoriteUseCase
import android.example.shop.domain.interactor.AddProductToViewedUseCase
import moxy.MvpPresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val addProductToFavoriteUseCase: AddProductToFavoriteUseCase,
    private val addProductToViewedUseCase: AddProductToViewedUseCase
): MvpPresenter<DescriptionView>() {
    fun addToCart(product: RemoteProduct) {
        addProductToCartUseCase(product)
    }

    fun addToFavorite(product: RemoteProduct) {
        addProductToFavoriteUseCase(product)
    }

    fun addToViewed(product: RemoteProduct) {
        addProductToViewedUseCase(product)
    }

    private fun showDetail() {
        viewState.showDetail()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        showDetail()
    }
}