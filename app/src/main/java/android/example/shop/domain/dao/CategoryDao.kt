package android.example.shop.domain.dao

import android.example.shop.domain.RemoteCategory
import android.example.shop.domain.RemoteProduct

interface CategoryDao {

    fun getCategoryProducts(category: String): List<RemoteProduct>

    fun getCategoryNames(): List<String>

    suspend fun getCategories(): List<RemoteCategory>
}