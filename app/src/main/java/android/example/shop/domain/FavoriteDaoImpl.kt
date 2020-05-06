package android.example.shop.domain

import android.example.shop.utils.FavoriteChangedEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class FavoriteDaoImpl @Inject constructor(
    /**
     * There should be Room database object
     */
): FavoriteDao {
    private val data: MutableList<RemoteProduct> = mutableListOf()

    override fun addProduct(product: RemoteProduct) {
        data.add(product)
        EventBus.getDefault().post(FavoriteChangedEvent(data.size))
    }

    override fun removeProduct(product: RemoteProduct) {
        data.remove(product)
        EventBus.getDefault().post(FavoriteChangedEvent(data.size))
    }

    override fun getProducts() = data
}