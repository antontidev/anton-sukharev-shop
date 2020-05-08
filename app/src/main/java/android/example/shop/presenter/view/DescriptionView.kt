package android.example.shop.presenter.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DescriptionView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showDetail()
}