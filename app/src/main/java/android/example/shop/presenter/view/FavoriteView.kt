package android.example.shop.presenter.view

import android.example.shop.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavoriteView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun deleteAllFavorite()

    @StateStrategyType(SkipStrategy::class)
    fun navigateToProductDetail(product: RemoteProduct)

    fun showFavoriteMenu(isEmpty: Boolean)

    fun showFavoriteProducts(products: List<RemoteProduct>)
}