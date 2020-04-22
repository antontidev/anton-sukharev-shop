package android.example.shop.presenter

import android.example.shop.R
import android.example.shop.model.TestShoppingCartItemModel
import moxy.MvpPresenter

class ShoppingCartPresenter: MvpPresenter<ShoppingCartView>() {
    val list = mutableListOf(
        TestShoppingCartItemModel("Iphone 8",
            "Beautiful phone",
            "Best phone ever maden. Add it to shopping cart",
            25000.0,
            R.drawable.common_full_open_on_phone),
        TestShoppingCartItemModel("Samsung S20",
            "New Samsung phone",
            "Cool phone with branch of brand new features. Good camera.",
            80000.0,
            R.drawable.common_google_signin_btn_icon_dark),
        TestShoppingCartItemModel("Meizu m6 note",
            "Good chinese phone.",
            "Not so good as you mentioned. Best choice for grandmas. You can buy it at your own risk.",
            10000.0,
            R.drawable.fui_ic_microsoft_24dp))

    fun setData() {
        viewState.setShoppingCart(list)
    }

    fun removeItem(string: TestShoppingCartItemModel) {
        val position = list.indexOf(string)
        list.remove(string)
        viewState.removeFromShoppingCart(position)
    }
}