package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface VisitedRecentlyView : MvpView {
    fun setRecentlyVisitedData(list: List<RemoteProduct>)

    fun navigateToProductDetail(product: RemoteProduct)
}