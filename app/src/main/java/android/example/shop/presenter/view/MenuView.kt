package android.example.shop.presenter.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MenuView: MvpView {
    fun showCartBadge(count: Int)


    fun showFavoriteBadge(count: Int)
}