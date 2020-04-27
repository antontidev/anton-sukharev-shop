package android.example.shop.presenter

import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsPresenter(
    private val mainApi: MainApi
) : BasePresenter<ProductsView>() {
    var data : List<RemoteProduct> = listOf()

    fun setData() {
        this.launch {
            val all = mainApi.allProducts("default").enqueue(object: Callback<List<RemoteProduct>> {
                override fun onFailure(call: Call<List<RemoteProduct>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<RemoteProduct>>,
                    response: Response<List<RemoteProduct>>
                ) {
                    data = response.body() ?: listOf()
                }
            })
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }
}