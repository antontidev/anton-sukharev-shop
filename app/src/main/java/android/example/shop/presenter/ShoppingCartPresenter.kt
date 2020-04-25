package android.example.shop.presenter

import android.example.shop.R
import android.example.shop.domain.model.TestShoppingCartItemModel
import moxy.MvpPresenter

class ShoppingCartPresenter : MvpPresenter<ShoppingCartView>() {
    val list = mutableListOf(
        TestShoppingCartItemModel(
            name = "Iphone 8",
            description = "Beautiful phone",
            fullDescription = "Best phone ever maden. Add it to shopping cart",
            price = 25000.0,
            discount = 20.0,
            id = R.drawable.common_full_open_on_phone
        ),
        TestShoppingCartItemModel(
            name = "Samsung S20",
            description = "New Samsung phone",
            fullDescription = "Cool phone with branch of brand new features. Good camera.",
            price = 80000.0,
            discount = 5.0,
            id = R.drawable.common_google_signin_btn_icon_dark
        ),
        TestShoppingCartItemModel(
            name = "Meizu m6 note",
            description = "Good chinese phone.",
            fullDescription = "Not so good as you mentioned. Best choice for grandmas. You can buy it at your own risk.",
            price = 10000.0,
            discount = 10.0,
            id = R.drawable.fui_ic_microsoft_24dp
        )
    )

    fun setData() {
        viewState.setShoppingCart(list)
    }

    fun removeItem(item: TestShoppingCartItemModel) {
        val position = list.indexOf(item)
        list.remove(item)
        viewState.removeFromShoppingCart(position)
    }

    fun addItem(item: TestShoppingCartItemModel) {
        list.add(item)
        val position = list.indexOf(item)
        viewState.addShoppingCartItem(position)
    }
}