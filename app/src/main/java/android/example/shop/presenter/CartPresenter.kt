package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.AddProductToCartUseCase
import android.example.shop.domain.interactor.GetCartProductsUseCase
import android.example.shop.domain.interactor.GetCartTotalPriceUseCase
import android.example.shop.domain.interactor.RemoveFromCartUseCase
import android.example.shop.presenter.view.CartView
import moxy.MvpPresenter
import javax.inject.Inject

class CartPresenter @Inject constructor(
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val removeCartProductUseCase: RemoveFromCartUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getCartTotalPriceUseCase: GetCartTotalPriceUseCase
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

    fun showCartTotalPrice() {
        val price = getCartTotalPriceUseCase()

        viewState.showTotalPrice(price)
    }

    fun addItem(product: RemoteProduct) {
        addProductToCartUseCase(product)
        val data = getCartProductsUseCase()
        val position = data.indexOf(product)
        viewState.addCartProduct(position)
    }

    fun showCart() {
        val cart = getCartProductsUseCase()

        val isEmpty = cart.isEmpty()
        viewState.showCart(isEmpty)
    }

    fun navigateToCheckout() = viewState.navigateToCheckout()

    fun getTotalPrice() = getCartTotalPriceUseCase()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
        showCart()
    }
}