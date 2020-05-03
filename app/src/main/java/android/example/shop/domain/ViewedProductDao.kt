package android.example.shop.domain

interface ViewedProductDao {

    /**
     * Save product as viewed
     */
    fun addProduct(product: RemoteProduct)

    /**
     * Get all viewed product list
     */
    fun getAllProducts(): List<RemoteProduct>

    /**
     * Clear list
     */
    fun clear()
}