package android.example.shop

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ShoppingCartView : MvpView{
    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(price: Double)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(name: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorFirstName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorSecondName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorMiddlename(visible: Boolean)
}
