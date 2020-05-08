package android.example.shop.presenter.view

import android.example.shop.domain.RemoteCategory
import android.example.shop.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategories(list: List<RemoteCategory>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setRecentlyVisitedData(list: List<RemoteProduct>)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToCategory(category: String)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToDetail(product: RemoteProduct)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToOrder()
}