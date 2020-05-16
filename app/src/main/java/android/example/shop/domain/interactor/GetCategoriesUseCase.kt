package android.example.shop.domain.interactor

import android.example.shop.domain.dao.CategoryDao
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val RemoteCategoryDao: CategoryDao
) {
    suspend operator fun invoke() = RemoteCategoryDao.getCategories()
}