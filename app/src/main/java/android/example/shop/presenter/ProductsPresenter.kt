package android.example.shop.presenter

import android.example.shop.domain.MainApi
import android.example.shop.domain.MarsProperty
import android.example.shop.domain.RemoteProduct
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProductsPresenter(
    private val mainApi: MainApi
) : BasePresenter<ProductsView>() {
    private var _data: MutableLiveData<List<RemoteProduct>> = MutableLiveData<List<RemoteProduct>>()
    val data: LiveData<List<RemoteProduct>>
        get() = _data

    private var _dataMarsProperty: MutableLiveData<List<MarsProperty>> = MutableLiveData<List<MarsProperty>>()

    val dataMarsProperty: LiveData<List<MarsProperty>>
        get() = _dataMarsProperty

    fun setCourseData() {
        launch {
            val all = mainApi.allProducts("default")
            _data.value = all
            viewState.setCourseProducts()
        }
    }

    private fun setMarsData() {
        launch {
            val all = mainApi.getPropertiesAsync()
            _dataMarsProperty.value = all
            viewState.setMarsProducts()
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //setData()
        setMarsData()
    }
}