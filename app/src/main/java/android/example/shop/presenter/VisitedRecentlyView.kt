package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.model.TestShoppingCartItemModel
import androidx.lifecycle.LiveData
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface VisitedRecentlyView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addRecentlyVisited()

    /**
     * Is it necessary?
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setRecentlyViewed(list: List<TestShoppingCartItemModel>)
}