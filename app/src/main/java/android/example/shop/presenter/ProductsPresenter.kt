package android.example.shop.presenter

import android.example.shop.domain.MainApi
import android.example.shop.domain.MarsProperty
import android.example.shop.domain.RemoteProduct
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ProductsPresenter @Inject constructor(
    private val mainApi: MainApi
) : BasePresenter<ProductsView>() {
    var data: List<RemoteProduct> = listOf()

    fun setData() {
        launch {
            val all = mainApi.allProducts("default")
            data = all
            viewState.setProducts(all)
        }
    }

    fun showProductDetail(item: RemoteProduct) {
        viewState.navigateToProductDetail(item)
    }

    private fun setMarsData() {

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
     //   setMarsData()
    }
}