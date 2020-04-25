package android.example.shop.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

@Parcelize
data class TestShoppingCartItemModel(
    /**
     * I wil use this property to get object from database in future
     * For know it solve problem of deleting items from shopping cart
     * When two items the same, the will be deleted together
     */
    val productId: Long = Random.nextLong(Long.MAX_VALUE),
    var name: String = "",
    var description: String = "",
    var fullDescription: String = "",
    var price: Double = 0.0,
    var discount: Double = 0.0,
    var id: Int = -1
): Parcelable