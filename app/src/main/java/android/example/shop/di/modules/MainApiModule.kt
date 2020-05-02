package android.example.shop.di.modules

import android.example.shop.domain.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainApiModule {
    @Provides
    @Singleton
    fun provideMainApi(): MainApi = Retrofit.Builder()
        //.baseUrl("https://mars.udacity.com/")
        .baseUrl("http://207.254.71.167:9191")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MainApi::class.java)
}