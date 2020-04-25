package android.example.shop.presenter

import android.example.shop.domain.model.TestShoppingCartItemModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface VisitedRecentlyView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setRecentlyViewed(list: List<TestShoppingCartItemModel>)
}