package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CheckoutView : MvpView {
    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorFirstName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorLastName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorMiddleName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorPhone(visible: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToDescription(product: RemoteProduct)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCartProducts(list: List<RemoteProduct>)
}
