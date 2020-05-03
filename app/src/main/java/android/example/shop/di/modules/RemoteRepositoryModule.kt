package android.example.shop.di.modules

import android.example.shop.domain.MainApi
import android.example.shop.domain.repository.RemoteRepository
import android.example.shop.domain.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RemoteRepositoryModule {
    @Inject
    lateinit var mainApi: MainApi

    @Provides
    @Singleton
    fun provideRemoteRepository(): RemoteRepository = RemoteRepositoryImpl(mainApi)
}