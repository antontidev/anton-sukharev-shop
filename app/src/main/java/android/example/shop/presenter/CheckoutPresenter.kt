package android.example.shop.presenter

import android.example.shop.domain.model.CreateOrderModel
import moxy.MvpPresenter

class CheckoutPresenter : MvpPresenter<CheckoutView>() {
    private fun checkSymbols(text: String) = text.length < 3

    private fun checkSymbolsPhone(text: String) = text.length > 12

    private fun checkFirstPhoneSymbol(text: String): Boolean {
        val firstSymbol = text.getOrElse(0) { 0.toChar() }
        return firstSymbol == '8'
    }

    private val model =
        CreateOrderModel()

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
}