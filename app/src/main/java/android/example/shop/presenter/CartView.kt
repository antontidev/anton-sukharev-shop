package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeCartProduct(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCartProducts(list: List<RemoteProduct>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addCartProduct(position: Int)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToProductDetail(product: RemoteProduct)
}