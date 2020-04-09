package android.example.shop

class ShoppingCartPresenter(
    private val view: ShoppingCartView
){
    private val iphoneCase = Product(price = 123.5, salePercent = 30, productName = "IPhone case")
    private val samsungCase = Product(price = 250.5, salePercent = 20, productName = "Samsung case")

    private var shoppingCart: ShoppingCart

    init {
        val productsList = listOf(iphoneCase, samsungCase)
        shoppingCart = ShoppingCart(productsList)
    }

    fun printPrices() {
        shoppingCart.forEachProduct{
            view.print(it.calcDiscountPrice())
        }
    }

    fun printProductsName() {
        shoppingCart.forEachProduct {
            view.print(it.getProductName())
        }
    }

    /**
     * Prints all products in shopping cart with total price
     */
    fun printShoppingCart() {
        shoppingCart.forEachProduct {
            view.print(it)
        }

        view.print(shoppingCart.calcProductsPrice())
    }
}