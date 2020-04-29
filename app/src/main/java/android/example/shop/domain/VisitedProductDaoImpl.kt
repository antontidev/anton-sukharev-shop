package android.example.shop.domain

import android.content.SharedPreferences
import android.example.shop.domain.model.TestShoppingCartItemModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VisitedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
) : VisitedProductDao {

    private var savedProductIds: List<TestShoppingCartItemModel>
        get() {
            val type = object : TypeToken<List<TestShoppingCartItemModel>>() {}.type
            val list = sharedPreferences.getString(PRODUCT_TAG, null)
            return Gson().fromJson(list, type) ?: listOf<TestShoppingCartItemModel>()
        }
        set(value) {
            sharedPreferences.edit().putString(PRODUCT_TAG, Gson().toJson(value)).apply()
        }

    override fun addProduct(product: TestShoppingCartItemModel) {
        val productIds: List<TestShoppingCartItemModel> = savedProductIds
        val newProductIds = mutableListOf<TestShoppingCartItemModel>().apply {
            add(0, product)
            addAll(productIds.filter { it != product })
        }
        savedProductIds = newProductIds
    }

    override fun getAllProducts(): List<TestShoppingCartItemModel> {
        return savedProductIds
    }

    override fun clear() {
        sharedPreferences.edit().putString(PRODUCT_TAG, null).apply()
    }

    companion object {
        const val PRODUCT_TAG = "PRODOUCT_TAG"
    }
}