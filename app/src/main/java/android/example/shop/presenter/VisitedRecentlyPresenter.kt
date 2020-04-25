package android.example.shop.presenter

import android.example.shop.utils.TestDataSetForAddingProducts
import moxy.MvpPresenter

class VisitedRecentlyPresenter : MvpPresenter<VisitedRecentlyView>() {
    /**
     * For testing
     */
    private val data = TestDataSetForAddingProducts().getAllItems()

    fun setData() {
        viewState.setRecentlyViewed(data)
    }

    fun setRecentlyViewed() {
        viewState.setRecentlyViewed(data)
    }
}