package android.example.shop.di.modules

import android.example.shop.domain.CartDao
import android.example.shop.domain.CartDaoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CartModule {
    @Provides
    @Singleton
    fun provideCart(): CartDao = CartDaoImpl()
}