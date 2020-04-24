package android.example.shop.domain

interface ViewProductDao {

    /**
     * Save product as viewed
     */
    fun addProduct(productId: Long)

    /**
     * Get all viewed product list
     */
    fun getAllProducts(): List<Long>
}