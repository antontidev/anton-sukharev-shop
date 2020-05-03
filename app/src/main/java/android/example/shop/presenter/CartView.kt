package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.model.TestShoppingCartItemModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeFromShoppingCart(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setShoppingCart(list: List<RemoteProduct>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addShoppingCartItem(position: Int)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToProductDetail(product: RemoteProduct)
}