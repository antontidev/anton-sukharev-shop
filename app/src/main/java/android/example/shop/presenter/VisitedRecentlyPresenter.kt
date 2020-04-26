package android.example.shop.presenter

import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.utils.TestDataSetForAddingProducts
import moxy.MvpPresenter

class VisitedRecentlyPresenter : MvpPresenter<VisitedRecentlyView>() {
    /**
     * For testing
     */
    private val data = TestDataSetForAddingProducts().getAllItems()

    fun addRecentlyVisited(item: TestShoppingCartItemModel) {
        data.add(item)
        val position = data.size
        viewState.addRecentlyVisited(position)
    }

    fun setData() {
        viewState.setRecentlyViewed(data)
    }
}