package android.example.shop.domain

import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.presenter.CategoryPresenter
import android.example.shop.presenter.DescriptionPresenter
import android.example.shop.presenter.VisitedRecentlyPresenter
import androidx.lifecycle.LiveData
import dagger.Component
import javax.inject.Singleton

interface VisitedProductDao {

    /**
     * Save product as viewed
     */
    fun addProduct(product: TestShoppingCartItemModel)

    /**
     * Get all viewed product list
     */
    fun getAllProducts(): List<TestShoppingCartItemModel>

    /**
     * Clear list
     */
    fun clear()
}