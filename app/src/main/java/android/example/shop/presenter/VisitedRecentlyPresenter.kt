package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.ViewedProductDao
import dagger.Module
import moxy.MvpPresenter
import javax.inject.Inject

@Module
class VisitedRecentlyPresenter @Inject constructor(
    private val viewedProductDao: ViewedProductDao
) : MvpPresenter<VisitedRecentlyView>() {

    fun showProductDetail(product: RemoteProduct) {
        viewState.navigateToProductDetail(product)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setRecentlyVisitedData(viewedProductDao.getAllProducts())
    }
}