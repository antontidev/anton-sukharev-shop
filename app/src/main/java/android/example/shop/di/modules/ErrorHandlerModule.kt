package android.example.shop.di.modules

import android.example.shop.domain.ErrorHandler
import android.example.shop.domain.ErrorHandlerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ErrorHandlerModule {
    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler = ErrorHandlerImpl()
}