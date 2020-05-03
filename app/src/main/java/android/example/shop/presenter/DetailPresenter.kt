package android.example.shop.presenter

import android.content.SharedPreferences
import android.example.shop.domain.RemoteProduct
import moxy.MvpPresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val sharedPreferences: SharedPreferences
): MvpPresenter<DescriptionView>() {
    fun addToCart(product: RemoteProduct) {
        viewState.addToCart(product)
    }

    fun addToFavorite(product: RemoteProduct) {
        viewState.addToFavorite(product)
    }

    fun addToViewed(product: RemoteProduct) {
        viewState.addToViewed(product)
    }

    private fun showDetail() {
        viewState.showDetail()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        showDetail()
    }
}