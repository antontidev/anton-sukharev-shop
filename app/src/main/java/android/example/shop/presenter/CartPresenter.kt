package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.AddProductToCartUseCase
import android.example.shop.domain.interactor.GetCartProductsUseCase
import android.example.shop.domain.interactor.RemoveProductFromCartUseCase
import moxy.MvpPresenter
import javax.inject.Inject

class CartPresenter @Inject constructor(
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val removeCartProductUseCase: RemoveProductFromCartUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : MvpPresenter<CartView>() {
    fun showProductDetail(product: RemoteProduct) {
        viewState.navigateToProductDetail(product)
    }

    fun setData() {
        val data = getCartProductsUseCase()
        viewState.setCartProducts(data)
    }

    fun removeItem(item: RemoteProduct) {
        val data = getCartProductsUseCase()
        val position = data.indexOf(item)
        removeCartProductUseCase(item)
        viewState.removeCartProduct(position)
    }

    fun addItem(product: RemoteProduct) {
        addProductToCartUseCase(product)
        val data = getCartProductsUseCase()
        val position = data.indexOf(product)
        viewState.addCartProduct(position)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }
}