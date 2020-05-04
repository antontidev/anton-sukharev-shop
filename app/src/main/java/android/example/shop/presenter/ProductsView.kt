package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProductsView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list: List<RemoteProduct>)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToProductDetail(item: RemoteProduct)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: String)
}