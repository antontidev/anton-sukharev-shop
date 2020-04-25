package android.example.shop.utils

import android.example.shop.R
import android.example.shop.domain.model.TestShoppingCartItemModel
import kotlin.random.Random

class TestDataSetForAddingProducts {
    private val data = mutableListOf(
        TestShoppingCartItemModel(
            name = "Leagoo Power",
            description = "Cheap chinese phone.",
            fullDescription = "Long battery life. Good camera. Big display. New Phone.",
            price = 7000.0,
            discount = 20.0,
            id = R.drawable.ic_error
        ),
        TestShoppingCartItemModel(
            name = "Dexp A240",
            description = "It costs your money.",
            fullDescription = "2 sim-cards, Bright TN-display. Good choice for temporary phone or for grandma.",
            price = 2000.0,
            discount = 15.0,
            id = R.drawable.ic_error
        ),
        TestShoppingCartItemModel(
            name = "Apple Iphone 11",
            description = "",
            fullDescription = "",
            price = 110000.0,
            discount = 10.0,
            id = R.drawable.ic_error
        ),
        TestShoppingCartItemModel(
            name = "Iphone 8",
            description = "Beautiful phone",
            fullDescription = "Best phone ever made. Add it to shopping cart",
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

    fun getAllItems() = data

    fun getNextItem(): TestShoppingCartItemModel {
        val index = Random.nextInt(data.size)
        return data[index]
    }
}