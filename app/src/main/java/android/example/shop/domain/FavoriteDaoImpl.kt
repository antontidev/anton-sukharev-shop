package android.example.shop.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class FavoriteDaoImpl @Inject constructor(
    /**
     * There should be Room database object
     */
): FavoriteDao {
    private val data: MutableList<RemoteProduct> = mutableListOf()
    private val count = MutableLiveData<Int>()
    override fun addToFavorite(product: RemoteProduct) {
        data.add(product)
        count.value = data.size
    }

    override fun removeFromFavorite(product: RemoteProduct) {
        data.remove(product)
        count.value = data.size
    }

    override fun getFavoriteProducts() = data

    override fun getFavoriteProductsCount() = count
}