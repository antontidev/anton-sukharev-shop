package android.example.shop.presenter

import android.example.shop.CreateOrderModel
import android.example.shop.Product
import android.example.shop.ShoppingCart
import moxy.MvpPresenter

class MainViewPresenter : MvpPresenter<MainView>(){
    private val iphoneCase = Product(
        price = 123.5,
        salePercent = 30,
        productName = "IPhone case"
    )
    private val samsungCase = Product(
        price = 250.5,
        salePercent = 20,
        productName = "Samsung case"
    )

    private fun checkSymbols(text: String) = text.length > 3

    private fun checkSymbolsPhone(text: String) = text.length > 12

    private fun checkFirstPhoneSymbol(text: String): Boolean {
        val firstSymbol = text.getOrElse(0) { 0.toChar() }
        return firstSymbol == '8'
    }

    private val model = CreateOrderModel()

    fun checkFirstName(text: String) {
        if (checkSymbols(text)) model.firstName = text
        viewState.showErrorFirstName(checkSymbols(text))
    }

    fun checkLastName(text: String) {
        if (checkSymbols(text)) model.lastName = text
        viewState.showErrorLastName(checkSymbols(text))
    }

    fun checkMiddleName(text: String) {
        if (checkSymbols(text)) model.middleName = text
        viewState.showErrorMiddleName(checkSymbols(text))
    }

    fun checkPhone(text: String) {
        val correctPhone = checkFirstPhoneSymbol(text) && checkSymbolsPhone(text)

        if (correctPhone) model.phone = text
        viewState.showErrorPhone(!correctPhone)
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