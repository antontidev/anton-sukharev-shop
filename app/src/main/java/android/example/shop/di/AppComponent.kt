package android.example.shop.di

import android.content.Context
import android.example.shop.di.modules.CartModule
import android.example.shop.di.modules.MainApiModule
import android.example.shop.di.modules.PreferencesModule
import android.example.shop.di.modules.RemoteRepositoryModule
import android.example.shop.ui.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        PreferencesModule::class,
        MainApiModule::class,
        RemoteRepositoryModule::class,
        CartModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context) : Builder
        fun build(): AppComponent
    }

    fun inject(fragment: ProductsFragment)
    fun inject(fragment: CatalogFragment)
    fun inject(fragment: DetailFragment)
    fun inject(fragment: CartFragment)
    fun inject(activity: MenuActivity)
}