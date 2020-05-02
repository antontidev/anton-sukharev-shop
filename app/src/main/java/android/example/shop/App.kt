package android.example.shop

import android.app.Application
import android.example.shop.di.AppComponent
import android.example.shop.di.DaggerAppComponent

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}