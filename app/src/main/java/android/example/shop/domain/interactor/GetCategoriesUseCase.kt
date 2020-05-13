package android.example.shop.domain.interactor

import android.example.shop.domain.dao.CategoryDao
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend operator fun invoke() = categoryDao.getCategories()
}