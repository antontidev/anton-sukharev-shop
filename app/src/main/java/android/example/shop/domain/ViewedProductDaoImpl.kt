package android.example.shop.domain

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ViewedProductDaoImpl(
    /**
     * Row of table should be here
     */
    private val sharedPreferences: SharedPreferences
) : ViewedProductDao {

    private var savedProductIds: List<RemoteProduct>
        get() {
            val type = object : TypeToken<List<RemoteProduct>>() {}.type
            val list = sharedPreferences.getString(PRODUCT_TAG, null)
            return Gson().fromJson(list, type) ?: listOf<RemoteProduct>()
        }
        set(value) {
            sharedPreferences.edit().putString(PRODUCT_TAG, Gson().toJson(value)).apply()
        }

    override fun addProduct(product: RemoteProduct) {
        val productIds: List<RemoteProduct> = savedProductIds
        val newProductIds = mutableListOf<RemoteProduct>().apply {
            add(0, product)
            addAll(productIds.filter { it != product })
        }
        savedProductIds = newProductIds
    }

    override fun getAllProducts(): List<RemoteProduct> {
        return savedProductIds
    }

    override fun clear() {
        sharedPreferences.edit().putString(PRODUCT_TAG, null).apply()
    }

    companion object {
        const val PRODUCT_TAG = "PRODOUCT_TAG"
    }
}