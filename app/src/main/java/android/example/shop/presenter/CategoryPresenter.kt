package android.example.shop.presenter

import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter() : BasePresenter<CategoryView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }
    var list = mutableListOf("Телевизоры", "Телефоны", "Планшеты")

    fun setData() {
        viewState.setCategories(list)
    }

    fun removeItem(string: String) {
        val position = list.indexOf(string)
        list.remove(string)
        viewState.removeItem(position)
    }
}