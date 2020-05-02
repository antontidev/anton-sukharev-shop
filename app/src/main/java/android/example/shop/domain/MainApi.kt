package android.example.shop.domain

import android.os.Parcelable
import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

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
    @Parcelize
    data class Attribute(
        val name: String,
        val value: String
    ): Parcelable
}

data class MarsProperty(
    val id: String,
    @SerializedName("img_src")
    val imgSrcUrl: String,
    val type: String,
    val price: Double)

interface MainApi {
    @GET("products/all/{author}")
    suspend fun allProducts(@Path("author") author: String): List<RemoteProduct>

    @GET("realestate")
    suspend fun getPropertiesAsync(): List<MarsProperty>
}