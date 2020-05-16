package android.example.shop.presenter

import android.example.shop.domain.Order
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.*
import android.example.shop.domain.model.CreateOrderModel
import android.example.shop.presenter.view.CheckoutView
import javax.inject.Inject

class CheckoutPresenter @Inject constructor(
    private val getCartTotalPriceUseCase: GetCartTotalPriceUseCase,
    private val getCartDiscountUseCase: GetCartDiscountUseCase,
    private val getCartPriceWithoutDiscountUseCase: GetCartPriceWithoutDiscountUseCase,
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val createOrderUseCase: AddOrderUseCase
) : BasePresenter<CheckoutView>() {
    private fun checkSymbols(text: String) = text.length < 3

    private fun checkSymbolsPhone(text: String) = text.length > 12

    private fun checkFirstPhoneSymbol(text: String): Boolean {
        val firstSymbol = text.getOrElse(0) { 0.toChar() }
        return firstSymbol == '8'
    }

    private val model =
        CreateOrderModel()

    fun setCashPaymentType() {
        model.paymentType = Order.PaymentType.CashOnReceiving
    }

    fun setCardPaymentType() {
        model.paymentType = Order.PaymentType.CardOnReceiving
    }
    fun checkFirstName(text: String) {
        if (!checkSymbols(text)) model.firstName = text
        viewState.showErrorFirstName(checkSymbols(text))
    }

    fun checkLastName(text: String) {
        if (!checkSymbols(text)) model.lastName = text
        viewState.showErrorLastName(checkSymbols(text))
    }

    fun checkPhone(text: String) {
        val correctPhone = checkFirstPhoneSymbol(text) && checkSymbolsPhone(text)

        if (correctPhone) model.phone = text
        viewState.showErrorPhone(!correctPhone)
    }

    fun showProductDetail(product: RemoteProduct) {
        viewState.navigateToDescription(product)
    }

    private fun setCartProducts() {
        val products = getCartProductsUseCase()

        viewState.setCartProducts(products)
    }

    fun setPrice() {
        val price = getCartPriceWithoutDiscountUseCase()

        viewState.showPrice(price)
    }

    fun setDiscount() {
        val discount = getCartDiscountUseCase()

        viewState.showDiscount(discount)
    }

    fun setPriceWithDiscount() {
        val priceWithDiscount = getCartTotalPriceUseCase()

        viewState.showPriceWithDiscount(priceWithDiscount)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setCartProducts()

        setDiscount()
        setPrice()
        setPriceWithDiscount()
    }

    fun createOrder() {
        launch {
            val cartProducts = getCartProductsUseCase()
            val orderItems = cartProducts.map {
                Order.Item(it.id, 1)
            }
            model.apply {
                val order = Order(
                    userFirstName = firstName,
                    userLastName = lastName,
                    userPhone = phone,
                    paymentType = paymentType,
                    items = orderItems
                )

                createOrderUseCase(order)
            }
        }

        viewState.showOrderInfo()
    }
}