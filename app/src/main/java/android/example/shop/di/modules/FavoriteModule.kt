package android.example.shop.di.modules

import android.example.shop.domain.FavoriteDao
import android.example.shop.domain.FavoriteDaoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoriteModule {
    @Provides
    @Singleton
    fun provideFavorite(): FavoriteDao = FavoriteDaoImpl()
}