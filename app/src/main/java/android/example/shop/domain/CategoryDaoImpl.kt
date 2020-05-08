package android.example.shop.domain

import android.example.shop.domain.dao.CategoryDao
import javax.inject.Inject

class CategoryDaoImpl @Inject constructor(
    private val mainApi: MainApi
) : CategoryDao {
    private var categories: List<RemoteCategory> = listOf()

    override fun getCategoryProducts(category: String): List<RemoteProduct> {
        return categories.find {
            it.name == category
        }?.products ?: listOf()
    }

    override fun getCategoryNames(): List<String> {
        return categories.map { it.name }
    }

    override suspend fun getCategories(): List<RemoteCategory> {
        categories = mainApi.allCategories("Sukharev")
        return categories
    }
}