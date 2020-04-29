package android.example.shop.presenter

import android.content.SharedPreferences
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.VisitedProductDaoImpl
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.utils.TestDataSetForAddingProducts
import dagger.Module
import moxy.MvpPresenter
import javax.inject.Inject

@Module
class VisitedRecentlyPresenter(
    private val sharedPreferences: SharedPreferences
) : MvpPresenter<VisitedRecentlyView>() {
    /**
     * For testing
     */
    private var visitedProductDaoImpl = VisitedProductDaoImpl(sharedPreferences)

    fun addRecentlyVisited(item: TestShoppingCartItemModel) {
        visitedProductDaoImpl.addProduct(item)
        setData()
    }

    fun setData() {
        val allProducts = visitedProductDaoImpl.getAllProducts()
        viewState.setRecentlyViewed(allProducts)
    }

    override fun onDestroy() {
        super.onDestroy()
        visitedProductDaoImpl.clear()
    }
}