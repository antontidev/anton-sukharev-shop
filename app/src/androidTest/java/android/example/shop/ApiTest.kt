package android.example.shop

import android.example.shop.di.AppComponent
import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ApiTest {
    @Inject
    lateinit var mainApi: MainApi

    interface ManagedCoroutineScope : CoroutineScope {
        abstract fun launch(block: suspend CoroutineScope.() -> Unit): Job
    }

    class TestScope(override val coroutineContext: CoroutineContext) : ManagedCoroutineScope {
        val scope = TestCoroutineScope(coroutineContext)
        override fun launch(block: suspend CoroutineScope.() -> Unit): Job {
            return scope.launch {
                block.invoke(this)
            }
        }
    }

    @Component(modules = [TestMainApiModule::class])
    internal interface TestAppComponent : AppComponent

    @Module
    internal class TestMainApiModule {
        @Provides
        fun provideMainApi() = Retrofit.Builder()
            .baseUrl("http://207.254.71.167:9191")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainApi::class.java)
    }
    @Test
    fun testProductAdd() {
        val product = RemoteProduct(
            id = Random.nextLong().toString(),
            name = "Test picture",
            price = 0.0,
            discountPercent = 0,
            description = "",
            imageUrl = "",
            attributes = listOf()
        )
        runBlockingTest {
            mainApi.addProductToCategory("Sukharev", "Test", product)

            assertNotNull(mainApi.allCategories("Sukhaarev").firstOrNull {
                it.name == "Test"
            }?.products?.firstOrNull {
                it == product
            })
        }
    }
}
