package android.example.shop.presenter

import android.example.shop.R
import android.example.shop.domain.RemoteProduct
import moxy.MvpPresenter

class CartPresenter : MvpPresenter<CartView>() {
    fun showProductDetail(product: RemoteProduct) {
        viewState.navigateToProductDetail(product)
    }

    fun setData() {
//        viewState.setShoppingCart(list)
    }

    fun removeItem(item: RemoteProduct) {
//        val position = list.indexOf(item)
//        list.remove(item)
//        viewState.removeFromShoppingCart(position)
    }

    fun addItem(item: RemoteProduct) {
//        list.add(item)
//        val position = list.indexOf(item)
//        viewState.addShoppingCartItem(position)
    }
}