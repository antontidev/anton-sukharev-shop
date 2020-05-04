package android.example.shop.presenter

import android.example.shop.domain.interactor.GetCartProductsCountUseCase
import android.example.shop.domain.interactor.GetFavoriteProductsUseCase
import moxy.MvpPresenter
import java.util.*
import javax.inject.Inject

class MenuPresenter @Inject constructor(
    private val getCartProductsCountUseCase: GetCartProductsCountUseCase,
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase
): MvpPresenter<MenuView>() {
    fun observeCartProductsCount() {
    }
}