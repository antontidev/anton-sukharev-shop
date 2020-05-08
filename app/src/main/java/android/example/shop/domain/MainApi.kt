package android.example.shop.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET
import retrofit2.http.Path

data class RemoteCategory(
    var name: String,
    var products: List<RemoteProduct>
)

@Parcelize
data class RemoteProduct(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val discountPercent: Int = 0,
    val description: String = "",
    val imageUrl: String = "",
    val attributes: List<Attribute> = listOf()
): Parcelable {
    /**
     * @return price with applied discount determined by [discountPercent]
     *
     * If [discountPercent] is more than 100 than product will have negative price
     * If [discountPercent] less than 0 product price will be increased
     */
    fun applyDiscount(): Double = price * (1 - discountPercent / 100.0)
    @Parcelize
    data class Attribute(
        val name: String,
        val value: String
    ): Parcelable
}

interface MainApi {
    @GET("products/all/{author}")
    suspend fun allProducts(@Path("author") author: String): List<RemoteProduct>

    @GET("products/allWithCategories/{author}")
    suspend fun allCategories(@Path("author") author: String): List<RemoteCategory>
}