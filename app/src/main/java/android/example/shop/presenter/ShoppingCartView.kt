package android.example.shop.presenter

import android.example.shop.model.TestShoppingCartItemModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ShoppingCartView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeFromShoppingCart(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setShoppingCart(list: List<TestShoppingCartItemModel>)
}