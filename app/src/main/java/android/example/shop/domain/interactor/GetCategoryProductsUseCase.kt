package android.example.shop.domain.interactor

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.CategoryDao
import javax.inject.Inject

class GetCategoryProductsUseCase @Inject constructor(
    private val categoryDao: CategoryDao,
    /**
     * For test
     */
    private val getCategoriesUseCase: GetCategoriesUseCase
) {
    //suspend operator fun invoke(category: String) = categoryDao.getCategoryProducts(category)
    suspend operator fun invoke(category: String): List<RemoteProduct> {
        val categories = getCategoriesUseCase()
        return categories.find {
            it.name == category
        }!!.products ?: listOf()
    }
}