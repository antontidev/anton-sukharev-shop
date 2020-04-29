package android.example.shop.presenter

import android.content.SharedPreferences
import android.example.shop.domain.VisitedProductDaoImpl
import android.example.shop.domain.model.TestShoppingCartItemModel
import dagger.Module
import moxy.MvpPresenter
import javax.inject.Inject

class DescriptionPresenter(
    private val sharedPreferences: SharedPreferences
): MvpPresenter<DescriptionView>() {
    private var visitedProductDaoImpl = VisitedProductDaoImpl(sharedPreferences)

    fun addVisited(item: TestShoppingCartItemModel) {
        visitedProductDaoImpl.addProduct(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        visitedProductDaoImpl.clear()
    }
}