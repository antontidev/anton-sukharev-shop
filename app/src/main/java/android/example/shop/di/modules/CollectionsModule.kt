package android.example.shop.di.modules

import android.example.shop.domain.CartDaoImpl
import android.example.shop.domain.FavoriteDaoImpl
import android.example.shop.domain.dao.CartDao
import android.example.shop.domain.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CollectionsModule {
    @Provides
    @Singleton
    fun provideFavorite(): FavoriteDao = FavoriteDaoImpl()

    @Provides
    @Singleton
    fun provideCart(): CartDao = CartDaoImpl()
}