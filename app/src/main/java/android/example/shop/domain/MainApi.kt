package android.example.shop.domain

import android.example.shop.Product
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class RemoteProduct(
    val id: String,
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>
) {
    data class Attribute(
        val name: String,
        val value: String
    )
}

interface MainApi {
    @GET("products/all/{author}")
    fun allProducts(@Path("author") author: String) : Call<List<RemoteProduct>>
}