package android.example.shop.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategories(list: List<String>)

    /**
     * For test
     * In real app this function useless
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(position: Int)
}