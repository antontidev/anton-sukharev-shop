package android.example.shop.utils

import android.example.shop.R
import android.example.shop.domain.model.TestShoppingCartItemModel
import kotlinx.android.synthetic.main.shopping_cart_fragment.view.*
import kotlin.random.Random

class TestDataSetForAddingProducts {
    val data = mutableListOf(TestShoppingCartItemModel(name = "Leagoo Power",
            description = "Cheap chinese phone.",
            fullDescription = "Long battery life. Good camera. Big display. New Phone.",
            price = 7000.0,
            discount = 20.0,
            id = R.drawable.ic_error),
        TestShoppingCartItemModel(name = "Dexp A240",
            description = "It costs your money.",
            fullDescription = "2 sim-cards, Bright TN-display. Good choice for temporary phone or for grandma.",
            price = 2000.0,
            discount = 15.0,
            id = R.drawable.ic_error),
        TestShoppingCartItemModel(name = "Apple Iphone 11",
            description = "",
            fullDescription = "",
            price = 110000.0,
            discount = 10.0,
            id = R.drawable.ic_error)
    )

    fun getNextItem(): TestShoppingCartItemModel {
        val index = Random.nextInt(data.size)
        return data[index]
    }
}