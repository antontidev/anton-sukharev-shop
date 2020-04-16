package android.example.shop

import moxy.MvpPresenter

class ShoppingCartPresenter : MvpPresenter<ShoppingCartView>(){
    private val iphoneCase = Product(price = 123.5, salePercent = 30, productName = "IPhone case")
    private val samsungCase = Product(price = 250.5, salePercent = 20, productName = "Samsung case")

    private fun checkSymbols(text: String) = text.length < 3

    private val model = CreateOrderModel()

    fun checkFirstName(text: String) {
        if (!checkSymbols(text)) model.firstName = text
        viewState.showErrorFirstName(checkSymbols(text))
    }

    fun checkSecondName(text: String) {
        if (!checkSymbols(text)) model.firstName = text
        viewState.showErrorFirstName(checkSymbols(text))
    }

    fun checkMiddleName(text: String) {
        if (!checkSymbols(text)) model.firstName = text
        viewState.showErrorFirstName(checkSymbols(text))
    }

    private var shoppingCart: ShoppingCart

    init {
        val productsList = listOf(iphoneCase, samsungCase)
        shoppingCart = ShoppingCart(productsList)
    }

    fun printPrices() {
        shoppingCart.forEachProduct{
            viewState.print(it.calcDiscountPrice())
        }
    }

    fun printProductsName() {
        shoppingCart.forEachProduct {
            viewState.print(it.getProductName())
        }
    }

    /**
     * Prints all products in shopping cart with total price
     */
    fun printShoppingCart() {
        shoppingCart.forEachProduct {
            viewState.print(it)
        }

        viewState.print(shoppingCart.calcProductsPrice())
    }
}