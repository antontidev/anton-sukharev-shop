package android.example.shop.presenter.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CreateProductView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun showResult()

    fun showErrorName(visible: Boolean)

    fun showErrorUrl(visible: Boolean)

    fun showErrorPrice(visible: Boolean)

    fun showErrorDescription(visible: Boolean)

    fun showErrorAuthor(visible: Boolean)
}