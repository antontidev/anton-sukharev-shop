package android.example.shop.presenter

import android.example.shop.domain.interactor.GetFavoriteProductsUseCase
import moxy.MvpPresenter
import javax.inject.Inject

class MenuPresenter @Inject constructor(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase
): MvpPresenter<MenuView>() {
    fun observeCartProductsCount() {
    }
}