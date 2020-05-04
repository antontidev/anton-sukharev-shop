package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.Skip

@StateStrategyType(AddToEndSingleStrategy::class)
interface DescriptionView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showDetail()
}