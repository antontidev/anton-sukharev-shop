package android.example.shop.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class Order(
    val userFirstName: String,
    val userLastName: String,
    val userPhone: String,
    val paymentType: PaymentType,
    val items: List<Item>
) {
    /**
     * @return [items] mapped to List<[FullOrder.Item]> with [Product]s returned by
     * passing [Item.productId] to [findProductById].
     * If [findProductById] returned null for [Item] from [items] than this item will not be presented in result list.
     */
    inline fun toFullOrder(findProductById: (String) -> RemoteProduct?): FullOrder {
        val fullOrderItems = items.mapNotNull { item ->
            item.toFullOrderItem(findProductById)
        }
        return FullOrder(
            userFirstName,
            userLastName,
            userPhone,
            paymentType,
            fullOrderItems
        )
    }

    data class Item(
        val productId: String,
        val count: Int
    ) {
        /**
         * @return [FullOrder.Item] with [Product] returned by [findProductById]
         * if this [Product] is not null and null otherwise.
         */
        inline fun toFullOrderItem(findProductById: (String) -> RemoteProduct?): FullOrder.Item? {
            val product = findProductById(productId)
            return if (product != null)
                FullOrder.Item(product, count)
            else
                null
        }
    }

    enum class PaymentType {
        CashOnReceiving, CardOnReceiving,
    }
}

data class FullOrder(
    val userFirstName: String,
    val userLastName: String,
    val userPhone: String,
    val paymentType: Order.PaymentType,
    val items: List<Item>
) {
    data class Item(
        val product: RemoteProduct,
        val count: Int
    )
}

data class RemoteCategory(
    val name: String,
    val products: List<RemoteProduct>
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

    @GET("products/allWithCategories/{author}/")
    suspend fun allCategories(@Path("author") author: String): List<RemoteCategory>

    @GET("orders/all/{author}/")
    suspend fun allOrders(@Path("author") author: String): List<FullOrder>

    @POST("products/allWithCategories/{author}/{category}")
    suspend fun addProductToCategory(
        @Path("author") author: String,
        @Path("category") category: String,
        @Body product: RemoteProduct
    )

    @POST("products/all/{author}/")
    suspend fun addProduct(@Path("author") author: String, @Body product: RemoteProduct)

    @POST("orders/new/{author}")
    suspend fun createOrder(@Path("author") author: String, @Body order: Order)
}