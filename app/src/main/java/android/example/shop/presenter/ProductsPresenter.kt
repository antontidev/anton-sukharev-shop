package android.example.shop.presenter

import android.example.shop.domain.MainApi
import android.example.shop.domain.MarsProperty
import android.example.shop.domain.RemoteProduct
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProductsPresenter(
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

    private fun setMarsData() {

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
     //   setMarsData()
    }
}