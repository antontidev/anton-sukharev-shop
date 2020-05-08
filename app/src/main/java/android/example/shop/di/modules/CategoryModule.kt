package android.example.shop.di.modules

import android.example.shop.domain.CategoryDaoImpl
import android.example.shop.domain.MainApi
import android.example.shop.domain.dao.CategoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [MainApiModule::class])
class CategoryModule {
    @Provides
    @Singleton
    fun provideCategories(mainApi: MainApi): CategoryDao = CategoryDaoImpl(mainApi)
}